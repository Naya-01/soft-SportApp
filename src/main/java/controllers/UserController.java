package controllers;

import java.util.logging.Logger;
import models.User;

public class UserController implements ControllerInterface{
  private Logger logger = Logger.getLogger("AuthController");
  private User userModel;
  private boolean isUIEnabled = false;

  public UserController() {
    this.userModel = new User();
  }

  public boolean upgradeAccount(){
    return userModel.upgradeAccount();
  }

  @Override
  public int activate(String[] deactivations, String[] activations) {
    return 0;
  }

  @Override
  public boolean enableUIView() {
    return false;
  }

  @Override
  public boolean disableUIView() {
    return false;
  }

  @Override
  public String[] getStateAsLog() {
    return new String[0];
  }
}
