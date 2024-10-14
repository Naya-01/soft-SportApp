package models;

public class Timer {
    private long startTime;
    private long endTime;
    private long currentTime;

    public Timer() {
        this.startTime = 0;
        this.endTime = 0;
        this.currentTime = 0;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return this.endTime - this.startTime;
    }

    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime() {
        this.currentTime = System.currentTimeMillis();
    }
}
