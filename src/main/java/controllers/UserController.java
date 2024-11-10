package controllers;

import java.util.logging.Logger;
import models.User;

public class UserController {
    private Logger logger = Logger.getLogger("AuthController");
    private User userModel;
    private boolean isUIEnabled = false;

    public UserController() {
        this.userModel = new User();
    }

    public boolean upgradeAccount(){
        return userModel.upgradeAccount();
    }

    public boolean updateProfile(String username, String password){
        return true;
    }
}
