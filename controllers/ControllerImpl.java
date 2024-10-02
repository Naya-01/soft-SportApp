package controllers;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerImpl implements ControllerInterface{

    public int activate(String[] deactivations, String[] activations){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : activate");
        return 0;
    }

    public boolean enableUIView(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : enableUIView");

        return true;
    }

    public boolean disableUIView(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : disableUIView");

        return true;
    }

    public String[] getStateAsLog(){
        Logger.getLogger("Log").log(Level.INFO, "ControllerImpl : getStateAsLog");

        return new String[0];
    }
}
