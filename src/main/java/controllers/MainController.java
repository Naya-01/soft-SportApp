package controllers;

import features.Feature;
import features.managers.FeatureManager;
import features.managers.ViewManager;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Log;

public class MainController implements ControllerInterface {
    private Logger logger;
    private FeatureManager featureManager;
    private ViewManager viewManager;

    public MainController() {
        Log.config();
        logger = Log.getLogger();
        logger.log(Level.INFO, "Server started");


        featureManager = FeatureManager.getInstance();
        viewManager = ViewManager.getInstance();
        logger.info("MainController initialisé");
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        boolean ok;
        int failed = 0;

        if (activations != null && activations.length > 0 && !activations[0].isEmpty()) {
            List<String> sortedFeatures = sortFeatures(activations);
            for (String feature : sortedFeatures) {
                ok = featureManager.activate(feature);
                if (ok) {
                    //logger.info("Feature activée: " + feature);
                } else {
                    logger.warning("Échec de l'activation de la feature: " + feature);
                    failed = -1;
                }
            }
        }

        if (deactivations != null && deactivations.length > 0 && !deactivations[0].isEmpty()) {
            List<String> sortedFeatures = sortFeatures(deactivations);
            for (String feature : sortedFeatures) {
                ok = featureManager.deactivate(feature);
                if (ok) {
                    //logger.info("Feature désactivée: " + feature);
                } else {
                    logger.warning("Échec de la désactivation de la feature: " + feature);
                    failed = -1;
                }
            }
        }

        // Pour le moment, on retourne simplement le statut
        return failed;
    }

    @Override
    public boolean enableUIView() {
        viewManager.setUIViewEnabled(true);
        return true;
    }

    @Override
    public boolean disableUIView() {
        viewManager.setUIViewEnabled(false);
        return true;
    }


    @Override
    public String[] getStateAsLog() {
        logger.info("Obtention de l'état actuel des features");
        Map<String, Feature> featureStates = featureManager.getFeatures();
        String[] logs = new String[featureStates.size()];
        int i = 0;
        for (Feature feature : featureStates.values()) {
            logs[i] = feature.getName() + "=" + feature.isActive();
            i++;
        }
        return logs;
    }

    private List<String> sortFeatures(String[] features) {
        List<String> featureList = Arrays.asList(features);

        Map<String, List<String>> dependencyGraph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        for (String featureName : featureList) {
            dependencyGraph.put(featureName, new ArrayList<>());
            inDegree.put(featureName, 0);
        }

        for (String featureName : featureList) {
            Feature feature = featureManager.getFeature(featureName);
            if (feature == null) {
                continue;
            }

            String dependOn = feature.getDependOn();
            if (dependOn != null && dependencyGraph.containsKey(dependOn)) {
                dependencyGraph.get(dependOn).add(featureName);
                inDegree.put(featureName, inDegree.get(featureName) + 1);
            }

            String parent = feature.getParentName();
            if (parent != null && dependencyGraph.containsKey(parent)) {
                dependencyGraph.get(parent).add(featureName);
                inDegree.put(featureName, inDegree.get(featureName) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String featureName : inDegree.keySet()) {
            if (inDegree.get(featureName) == 0) {
                queue.add(featureName);
            }
        }

        List<String> sortedList = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            sortedList.add(current);

            for (String dependent : dependencyGraph.get(current)) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    queue.add(dependent);
                }
            }
        }

        if (sortedList.size() != featureList.size()) {
            return null;
        }

        return sortedList;
    }
}
