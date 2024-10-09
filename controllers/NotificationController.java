package controllers;

import models.Notification;

public class NotificationController implements ControllerInterface {
    private Notification notification;

    public NotificationController(Notification notification) {
        this.notification = notification;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        // Activation logic here
        return 0;
    }

    @Override
    public boolean enableUIView() {
        return true;
    }

    @Override
    public boolean disableUIView() {
        return false;
    }

    @Override
    public String[] getStateAsLog() {
        return new String[]{notification.getMessage()};
    }
}
