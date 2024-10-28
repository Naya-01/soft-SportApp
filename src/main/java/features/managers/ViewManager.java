package features.managers;

import features.ConstraintType;
import features.Feature;
import java.util.*;
import java.util.logging.Logger;

public class ViewManager {
    private static ViewManager instance = null;
    private FeatureManager featureManager;
    private boolean uiViewEnabled;
    private Logger logger;
    private Map<String, String> viewGroupMapping; // Map des vues vers les groupName

    private ViewManager() {
        logger = Logger.getLogger(this.getClass().getName());
        featureManager = FeatureManager.getInstance();
        uiViewEnabled = true; // Par défaut, les vues UI sont activées
        initializeViewGroupMapping();
        logger.info("ViewManager initialisé");
    }

    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    private void initializeViewGroupMapping() {
        viewGroupMapping = new HashMap<>();

        viewGroupMapping.put("difficultyView", "difficulty");
        viewGroupMapping.put("typeView", "type");
        viewGroupMapping.put("paymentView", "payment_method");
    }

    public void setUIViewEnabled(boolean enabled) {
        this.uiViewEnabled = enabled;
        logger.info("UIView " + (enabled ? "activée" : "désactivée") + " dans ViewManager");
    }

    public boolean isViewActive(String viewName) {
        if (!uiViewEnabled) {
            logger.info("UIView est désactivée globalement");
            return false;
        }

        String groupName = viewGroupMapping.get(viewName);

        if (groupName == null) {
            logger.info("Vue " + viewName + " est active (aucun groupName associé)");
            return true;
        }

        List<Feature> groupFeatures = featureManager.getFeaturesByGroup(groupName);
        ConstraintType constraintType = featureManager.getConstraintTypeByGroup(groupName);

        if (groupFeatures.isEmpty()) {
            logger.info("Vue " + viewName + " est active (groupe sans features)");
            return true;
        }

        boolean isActive = false;

        if (constraintType != null) {
            switch (constraintType) {
                case ALTERNATIVE:
                case OR:
                    isActive = groupFeatures.stream().anyMatch(Feature::isActive);
                    break;
                case MANDATORY:
                    isActive = groupFeatures.stream().allMatch(Feature::isActive);
                    break;
                case OPTIONAL:
                    isActive = groupFeatures.stream().anyMatch(Feature::isActive);
                    break;
                default:
                    isActive = groupFeatures.stream().anyMatch(Feature::isActive);
                    break;
            }
        } else {
            isActive = groupFeatures.stream().anyMatch(Feature::isActive);
        }

        logger.info("Vue " + viewName + " est " + (isActive ? "active" : "inactive"));
        return isActive;
    }
}
