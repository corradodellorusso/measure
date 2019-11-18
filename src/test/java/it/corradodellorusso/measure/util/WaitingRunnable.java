package it.corradodellorusso.measure.util;

public class WaitingRunnable implements Runnable {

    private long sleepTimeInMillis;

    public WaitingRunnable(long sleepTimeInMillis) {
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public void run() {
        ThreadUtils.sleep(sleepTimeInMillis);
    }
}
