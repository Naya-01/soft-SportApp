package controllers;

import models.User;
import models.domains.UserDTO;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthController implements ControllerInterface {
    private Logger logger = Logger.getLogger("AuthController");
    private User userModel;
    private boolean isUIEnabled = false;

    public AuthController() {
        userModel = new User();
    }

    public boolean login(String name, String password) {
        UserDTO user = userModel.getUser(name);

        if (user == null) {
            logger.log(Level.WARNING, "Login failed: User not found - " + name);
            return false;
        }

        if (!userModel.checkPassword(user, password)) {
            logger.log(Level.WARNING, "Login failed: Incorrect password - " + name);
            return false;
        }

        if (userModel.isConnected()) {
            logger.log(Level.WARNING, "Login failed: User already connected - " + name);
            return false;
        }

        userModel.connect(user);
        logger.log(Level.INFO, "Login successful for user: " + name);
        return true;
    }

    public void logout() {
        if (!userModel.isConnected()) {
            logger.log(Level.WARNING, "Logout failed: User not connected");
            return;
        }

        userModel.disconnect();
        logger.log(Level.INFO, "Logout successful");
    }

    public boolean register(String name, String password, boolean isPremium) {
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            logger.log(Level.WARNING, "Register failed: Fields missing or empty");
            return false;
        }

        if (userModel.getUser(name) != null) {
            logger.log(Level.WARNING, "Register failed: User already exists - " + name);
            return false;
        }

        userModel.addUser(name, password, isPremium);
        logger.log(Level.INFO, "Register successful for user: " + name);
        return true;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        logger.log(Level.INFO, "Activating/deactivating authentication...");
        for (String activation : activations) {
            if (activation.equalsIgnoreCase("Auth")) {
                logger.log(Level.INFO, "Authentication feature activated.");
                return 0;
            }
        }

        for (String deactivation : deactivations) {
            if (deactivation.equalsIgnoreCase("Auth")) {
                logger.log(Level.INFO, "Authentication feature deactivated.");
                logout();
                return 0;
            }
        }

        return 1; // Aucun changement
    }

    @Override
    public boolean enableUIView() {
        if (isUIEnabled) {
            logger.log(Level.INFO, "Auth UI already enabled.");
            return false;
        }

        logger.log(Level.INFO, "Enabling authentication UI...");
        isUIEnabled = true;
        return true;
    }

    @Override
    public boolean disableUIView() {
        if (!isUIEnabled) {
            logger.log(Level.INFO, "Auth UI already disabled.");
            return false;
        }

        logger.log(Level.INFO, "Disabling authentication UI...");
        isUIEnabled = false;
        return true;
    }

    @Override
    public String[] getStateAsLog() {
        return null;
    }
}
