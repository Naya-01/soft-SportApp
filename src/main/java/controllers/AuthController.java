package controllers;

import models.User;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthController implements ControllerInterface {
    private User currentUser;
    private Logger logger = Logger.getLogger("AuthController");
    private boolean isUIEnabled = false;

    public AuthController() {
        this.currentUser = null;
    }

    public boolean login(String name, String password) {
        List<User> users = JsonDBUtil.readFromJson(User.class);

        for (User user : users) {
            if (user.getName().equals(name) && verifyPassword(user, password)) {
                currentUser = JsonDBUtil.findObjectInJson(User.class, "name", name);
                logger.log(Level.INFO, "Login successful for user: " + name);

                UserSessionManager.getInstance().addUser(currentUser);
                return true;
            }
        }

        logger.log(Level.WARNING, "Login failed for user: " + name);
        return false;
    }

    private boolean verifyPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public void logout() {
        if (currentUser != null) {
            logger.log(Level.INFO, "User logged out: " + currentUser.getName());

            UserSessionManager.getInstance().removeUser(currentUser);
            currentUser = null;
        } else {
            logger.log(Level.INFO, "No user currently logged in.");
        }
    }

    public boolean register(String name, String password, String email, String address, boolean isPremium) {
        User existingUser = JsonDBUtil.findObjectInJson(User.class, "name", name);
        if (existingUser != null) {
            logger.log(Level.WARNING, "User already exists: " + name);
            return false;
        }

        User newUser = new User(name, password, email, address, isPremium);
        // PAS OUBLIER SET ID

        JsonDBUtil.addObjectToJson(newUser, User.class);

        logger.log(Level.INFO, "User registered successfully: " + name);
        return true;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        } else {
            logger.log(Level.INFO, "No user logged in.");
            return null;
        }
    }

    public boolean isAuthenticated() {
        return currentUser != null;
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
        if (isAuthenticated()) {
            return new String[] { "User: " + currentUser.getName() + " (Premium: " + currentUser.getIsPremium() + ")" };
        } else {
            return new String[] { "No user authenticated." };
        }
    }

    public List<User> getConnectedUsers() {
        List<User> connectedUsers = UserSessionManager.getInstance().getConnectedUsers();
        return connectedUsers;
    }
}
