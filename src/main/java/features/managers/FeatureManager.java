package features.managers;


import static features.ConstraintType.ALTERNATIVE;
import static features.ConstraintType.MANDATORY;

import features.AbstractFeature;
import features.ConstraintType;
import features.Feature;
import features.observers.UIViewObserver;
import features.strategies.AlternativeStrategy;
import features.strategies.DefaultStrategy;
import features.strategies.FeatureStrategy;
import features.strategies.OrStrategy;
import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureManager extends StateManager{

    private static FeatureManager instance = null;
    private Map<String, Feature> features;
    private static final String FEATURES_PACKAGE = "features.commands";

    private List<UIViewObserver> observers;

    public FeatureManager() {
        super();
        observers = new ArrayList<>();
        features  = new HashMap<>();
        fillFeaturesMap();
    }

    public static FeatureManager getInstance() {
        if (instance == null) {
            instance = new FeatureManager();
        }
        return instance;
    }
    public void addObserver(UIViewObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(UIViewObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String featureName, boolean isActive) {
        for (UIViewObserver observer : new ArrayList<>(observers)) {
            observer.onFeatureStateChanged(featureName, isActive);
        }
    }


    public void fillFeaturesMap() {
        try {
            String path = getClass().getClassLoader().getResource(FEATURES_PACKAGE.replace(".", "/")).getPath();
            File directory = new File(path);

            if (!directory.exists() || !directory.isDirectory()) {
                logger.warning("Le répertoire des features n'existe pas : " + path);
                return;
            }

            loadFeaturesRecursively(directory, FEATURES_PACKAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFeaturesRecursively(File directory, String packageName) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                loadFeaturesRecursively(file, packageName + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String className = file.getName().replace(".class", "");
                String fullClassName = packageName + "." + className;

                try {
                    Class<?> clazz = Class.forName(fullClassName);

                    if (!Modifier.isAbstract(clazz.getModifiers()) && AbstractFeature.class.isAssignableFrom(clazz)) {
                        Feature featureInstance = (Feature) clazz.getDeclaredConstructor().newInstance();
                        String featureName = featureInstance.getName();
                        features.put(featureName, featureInstance);
                        logger.info("Feature chargée : " + featureName);
                    }
                } catch (Exception e) {
                    logger.warning("Erreur lors du chargement de la feature : " + fullClassName);
                }
            }
        }
    }

    private FeatureStrategy getStrategy(Feature feature) {
        if (feature.getConstraintType() != null) {
            return switch (feature.getConstraintType()) {
                case ALTERNATIVE -> new AlternativeStrategy();
                case OR -> new OrStrategy();
                default -> new DefaultStrategy();
            };
        }
        return new DefaultStrategy();
    }

    @Override
    public boolean activate(String featureName) {
        Feature feature = features.get(featureName);
        if (feature == null) {
            logger.warning("Feature non trouvée : " + featureName);
            return false;
        }
        if (feature.isActive()) {
            logger.info("Feature déjà active : " + featureName);
            return true;
        }

        if(feature.getDependOn()!=null && !CheckFeatureIsActive(feature.getDependOn())){
            logger.warning("Impossible d'activer la feature : " + featureName + " car elle dépend de la feature : " + feature.getDependOn());
            return false;
        }

        if(feature.getParentName()!=null &&!CheckFeatureIsActive(feature.getParentName())){
            logger.warning("Impossible d'activer la feature : " + featureName + " car elle est une sous-feature de la feature : " + feature.getParentName());
            return false;
        }

        FeatureStrategy strategy = getStrategy(feature);
        strategy.activateFeature(feature, features);

        activateChildFeatures(feature.getName());
        activateDependents(feature.getName());

        notifyObservers(featureName, true);
        return true;
    }

    @Override
    public boolean deactivate(String featureName) {
        Feature feature = features.get(featureName);
        if (feature == null) {
            logger.warning("Feature non trouvée : " + featureName);
            return false;
        }
        if (!feature.isActive()) {
            logger.info("Feature déjà désactivée : " + featureName);
            return true;
        }

        if (feature.getConstraintType().equals(MANDATORY) && feature.getParentName() == null) {
            logger.warning("Impossible de désactiver une feature MANDATORY : " + featureName);
            return false;
        }

        FeatureStrategy strategy = getStrategy(feature);
        strategy.deactivateFeature(feature, features);

        if (!feature.getConstraintType().equals(MANDATORY) && feature.getParentName() == null) {
            deactivateChildFeatures(feature);
            deactivateDependents(feature);
        }

        notifyObservers(featureName, false);
        return true;
    }

    public List<Feature> getFeaturesByGroup(String groupName) {
        List<Feature> groupFeatures = new ArrayList<>();
        for (Feature feature : features.values()) {
            if (groupName.equals(feature.getGroupName())) {
                groupFeatures.add(feature);
            }
        }
        return groupFeatures;
    }

    public List<Feature> getFeaturesByParent(String parentName) {
        List<Feature> groupFeatures = new ArrayList<>();
        for (Feature feature : features.values()) {
            if (parentName.equals(feature.getParentName())) {
                groupFeatures.add(feature);
            }
        }
        return groupFeatures;
    }

    public List<Feature> getFeaturesByDependOn(String depend_on) {
        List<Feature> groupFeatures = new ArrayList<>();
        for (Feature feature : features.values()) {
            if (depend_on.equals(feature.getDependOn())) {
                groupFeatures.add(feature);
            }
        }
        return groupFeatures;
    }

    public ConstraintType getConstraintTypeByGroup(String groupName) {
        for (Feature feature : features.values()) {
            if (groupName.equals(feature.getGroupName())) {
                return feature.getConstraintType();
            }
        }
        return null;
    }

    @Override
    public boolean isActive(String featureName) {
        Feature feature = features.get(featureName);
        return feature != null && feature.isActive();
    }

    public Map<String, Feature> getFeatures() {
        return features;
    }

    public static Feature getFeature(String featureName) {
        return getInstance().getFeatures().get(featureName);
    }

    private void deactivateChildFeatures(Feature parentFeature) {
        for (Feature feature : getFeaturesByGroup(parentFeature.getName())) {
            if (parentFeature.getName().equals(feature.getParentName())) {
                if (feature.isActive()) {
                    deactivate(feature.getName());
                    //logger.info("Feature enfant désactivée : " + feature.getName());
                }
            }
        }
    }

    private void deactivateDependents(Feature feature) {
        for (Feature f : getFeaturesByDependOn(feature.getName())) {
            if (feature.getName().equals(f.getDependOn())) {
                if (f.isActive()) {
                    deactivate(f.getName());
                }
            }
        }
    }

    private void activateChildFeatures(String featureName) {
        for (Feature feature : getFeaturesByParent(featureName)) {
            if (!feature.isActive() && feature.getConstraintType() == MANDATORY) {
                activate(feature.getName());
            }
        }
    }

    private void activateDependents(String featureName) {
        for (Feature feature : getFeaturesByDependOn(featureName)) {
            if (!feature.isActive() && feature.getConstraintType() == MANDATORY && getFeature(feature.getParentName()).isActive()) {
                activate(feature.getName());
            }
        }
    }

    private boolean CheckFeatureIsActive(String featureName) {
        Feature feature = features.get(featureName);
        return feature.isActive();
    }
}
