package features.managers;


import static features.ConstraintType.ALTERNATIVE;

import features.AbstractFeature;
import features.ConstraintType;
import features.Feature;
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

    public FeatureManager() {
        super();

        features  = new HashMap<>();
        fillFeaturesMap();
    }

    public static FeatureManager getInstance() {
        if (instance == null) {
            instance = new FeatureManager();
        }
        return instance;
    }

    private void fillFeaturesMap() {
        try {
            String path = getClass().getClassLoader().getResource(FEATURES_PACKAGE.replace(".", "/")).getPath();
            File directory = new File(path);

            if (!directory.exists() || !directory.isDirectory()) {
                logger.warning("Le répertoire des features n'existe pas : " + path);
                return;
            }

            for (File file : directory.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = file.getName().replace(".class", "");
                    String fullClassName = FEATURES_PACKAGE + "." + className;

                    Class<?> clazz = Class.forName(fullClassName);

                    if (!Modifier.isAbstract(clazz.getModifiers()) && AbstractFeature.class.isAssignableFrom(clazz)) {
                        try {
                            if (clazz.getDeclaredConstructor() != null) {
                                Feature featureInstance = (Feature) clazz.getDeclaredConstructor().newInstance();
                                String featureName = featureInstance.getName();
                                features.put(featureName, featureInstance);
                                logger.info("Feature chargée : " + featureName);
                            }
                        } catch (NoSuchMethodException e) {
                            logger.warning("Aucun constructeur par défaut trouvé pour la classe : " + className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        FeatureStrategy strategy = getStrategy(feature);
        strategy.activateFeature(feature, features);
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

        if (feature.isMandatory() && feature.getParentName() == null) {
            logger.warning("Impossible de désactiver une feature MANDATORY : " + featureName);
            return false;
        }

        if (!feature.isMandatory() && feature.getParentName() == null) {
            deactivateChildFeatures(feature);
        }
        FeatureStrategy strategy = getStrategy(feature);
        strategy.deactivateFeature(feature, features);
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

    private void deactivateChildFeatures(Feature parentFeature) {
        for (Feature feature : getFeaturesByGroup(parentFeature.getName())) {
            if (parentFeature.getName().equals(feature.getParentName())) {
                if (feature.isActive()) {
                    deactivate(feature.getName());
                    logger.info("Feature enfant désactivée : " + feature.getName());
                }
            }
        }
    }
}
