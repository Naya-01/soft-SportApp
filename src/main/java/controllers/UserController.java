package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import features.managers.FeatureManager;
import features.FeaturesEnum;
import models.User;

public class UserController {
    private Logger logger = Logger.getLogger("AuthController");
    private User userModel;
    private FeatureManager featureManager;

    public UserController() {
        this.userModel = new User();
        featureManager = FeatureManager.getInstance();
    }

    public boolean upgradeAccount(){
        return userModel.upgradeAccount();
    }

    public boolean setUsername(String username) {
        if (!featureManager.isActive(FeaturesEnum.SET_USERNAME.getFeature())) {
            logger.warning("set_username feature is disabled.");
            return false;
        }

        if (username == null || username.isEmpty()) {
            logger.log(Level.WARNING, "Set username failed: Fields missing or empty");
            return false;
        }

        return userModel.setUsername(username);
    }

    public boolean setPassword(String password) {
        if (!featureManager.isActive(FeaturesEnum.SET_PASSWORD.getFeature())) {
            logger.warning("set_password feature is disabled.");
            return false;
        }

        if (password == null || password.isEmpty()) {
            logger.log(Level.WARNING, "Set password failed: Fields missing or empty");
            return false;
        }

        return userModel.setPassword(password);
    }
}
