package models;

public class Timer {
    private long startTime;

    public Timer() {
        this.startTime = 0;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - this.startTime;
    }

    public void reset() {
        this.startTime = 0;
    }

    public long getStartTime() {
        return this.startTime;
    }
}
