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

    public boolean setUsername(String username) {
        if (username == null || username.isEmpty()) {
            logger.log(Level.WARNING, "Set username failed: Fields missing or empty");
            return false;
        }

        return userModel.setUsername(username);
    }

    public boolean setPassword(String password) {
        if (password == null || password.isEmpty()) {
            logger.log(Level.WARNING, "Set password failed: Fields missing or empty");
            return false;
        }

        return userModel.setPassword(password);
    }
}
