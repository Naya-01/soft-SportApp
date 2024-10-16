package controllers;

import models.User;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthController implements ControllerInterface {
    private Logger logger = Logger.getLogger("AuthController");
    private static final String USER_FILE_PATH = "src/main/java/data/users.json";
    private boolean isUIEnabled = false;

    public AuthController() {}

    public boolean login(String name, String password) {
        List<User> users = JsonDBUtil.readFromJson(USER_FILE_PATH, User.class);

        for (User user : users) {
            if (user.getName().equals(name) && user.verifyPassword(password)) {
                if (UserSessionManager.getInstance().isUserConnected(user)) {
                    logger.log(Level.WARNING, "User already connected: " + name);
                    return false;
                }
                User loggedUser = UserSessionManager.setUserConnected(JsonDBUtil.findObjectInJson(USER_FILE_PATH,"name", name, User.class));
                logger.log(Level.INFO, "Login successful for user: " + name);

                UserSessionManager.getInstance().addConnectedUser(loggedUser);
                return true;
            }
        }

        logger.log(Level.WARNING, "Login failed for user: " + name);
        return false;
    }

    public void logout() {
        if (UserSessionManager.currentUser != null) {
            logger.log(Level.INFO, "User logged out: " + UserSessionManager.currentUser.getName());

            UserSessionManager.getInstance().removeUser(UserSessionManager.currentUser);
            UserSessionManager.currentUser = null;
        } else {
            logger.log(Level.INFO, "No user currently logged in.");
        }
    }

    public boolean register(String name, String password, String email, String address, boolean isPremium) {
        User existingUser = JsonDBUtil.findObjectInJson(USER_FILE_PATH,"name", name, User.class);
        if (existingUser != null) {
            logger.log(Level.WARNING, "User already exists: " + name);
            return false;
        }

        User newUser = new User(name, password, email, address, isPremium);
        newUser.setId(UUID.randomUUID());

        JsonDBUtil.addObjectToJson(USER_FILE_PATH, newUser, User.class);

        logger.log(Level.INFO, "User registered successfully: " + name);
        return true;
    }

    public boolean isAuthenticated() {
        return UserSessionManager.currentUser != null;
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
            return new String[] { "User: " + UserSessionManager.currentUser.getName() +
                    " (Premium: " + UserSessionManager.currentUser.getIsPremium() + ")" };
        } else {
            return new String[] { "No user authenticated." };
        }
    }

    public List<User> getConnectedUsers() {
        List<User> connectedUsers = UserSessionManager.getInstance().getConnectedUsers();
        return connectedUsers;
    }
}
