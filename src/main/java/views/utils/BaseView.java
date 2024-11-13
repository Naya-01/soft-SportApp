package views.utils;

import features.managers.ViewManager;
import features.observers.UIViewObserver;
import utils.Log;

import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;
import javax.swing.*;

public abstract class BaseView extends JFrame implements UIViewObserver {
    protected Map<String, Consumer<Boolean>> featureUpdateHandlers;

    protected Logger logger;
    public BaseView() {
        ViewManager.getInstance().addObserver(this);
        logger = Log.getLogger();
        if (!ViewManager.getInstance().isUIViewEnabled()) {
            this.setVisible(false);
        }
    }

    @Override
    public void onUIViewStateChanged(final boolean enabled) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (enabled) {
                    setVisible(true);
                } else {
                    setVisible(false);
                }
            }
        });
    }

    @Override
    public void dispose() {
        ViewManager.getInstance().removeObserver(this);
        super.dispose();
    }

    @Override
    public void onFeatureStateChanged(String featureName, boolean isActive) {
        logger.info("Feature state changed: " + featureName + " to " + isActive);
        Consumer<Boolean> handler = featureUpdateHandlers.get(featureName);
        if (handler != null) {
            SwingUtilities.invokeLater(() -> handler.accept(isActive));
        }
    }
}
