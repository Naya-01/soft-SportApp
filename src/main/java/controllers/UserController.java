package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class UserController {
    private Logger logger = Logger.getLogger("AuthController");
    private User userModel;

    public UserController() {
        this.userModel = new User();
    }

    public boolean upgradeAccount(){
        return userModel.upgradeAccount();
    }

    public boolean updateProfile(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            logger.log(Level.WARNING, "Add Exercice failed: Fields missing or empty");
            return false;
        }

        return userModel.updateProfile(username, password);
    }
}
