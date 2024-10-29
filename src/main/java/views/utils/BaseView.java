package views.utils;

import features.managers.ViewManager;
import features.observers.UIViewObserver;

import javax.swing.*;

public abstract class BaseView extends JFrame implements UIViewObserver {
    public BaseView() {
        ViewManager.getInstance().addObserver(this);
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
}
