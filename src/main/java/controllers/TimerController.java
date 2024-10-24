package controllers;

import models.Timer;
import features.FeatureManager;
import features.FeaturesEnum;

public class TimerController {

    private Timer timer;
    private FeatureManager featureManager;
    private boolean isTimerRunning;


    public TimerController() {
        this.timer = new Timer();
        this.featureManager = FeatureManager.getInstance();
        this.isTimerRunning = false;
    }


    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void setTimerRunning(boolean isTimerRunning) {
        this.isTimerRunning = isTimerRunning;
    }

    public void startTimer() {
        timer.start();
    }

    public void resetTimer() {
        timer.reset();
    }

    public long getElapsedTime() {
        return timer.getElapsedTime();
    }

    public boolean isTimerActive() {
        return featureManager.isFeatureActive(FeaturesEnum.EXERCICE_TIMER);
    }

    public long getStartTime() {
        return timer.getStartTime();
    }

}
