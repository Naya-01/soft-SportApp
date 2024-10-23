package controllers;

import models.User;
import models.domains.UserDTO;
import models.domains.UserViewDTO;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthController {
    private Logger logger = Logger.getLogger("AuthController");
    private User userModel;

    public AuthController() {
        userModel = new User();
    }

    public UserViewDTO login(String name, String password) {
        UserDTO user = userModel.getUser(name);

        if (user == null) {
            logger.log(Level.WARNING, "Login failed: User not found - " + name);
            return null;
        }

        if (!userModel.checkPassword(user, password)) {
            logger.log(Level.WARNING, "Login failed: Incorrect password - " + name);
            return null;
        }

        if (userModel.isConnected()) {
            logger.log(Level.WARNING, "Login failed: User already connected - " + name);
            return null;
        }

        userModel.connect(user);
        logger.log(Level.INFO, "Login successful for user: " + name);
        return userModel.parseToUserViewDTO(user);
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
}
