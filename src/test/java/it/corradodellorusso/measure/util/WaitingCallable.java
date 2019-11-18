package it.corradodellorusso.measure.util;

import java.util.concurrent.Callable;

public class WaitingCallable implements Callable<Void> {

    private long sleepTimeInMillis;

    public WaitingCallable(long sleepTimeInMillis) {
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public Void call() throws Exception {
        ThreadUtils.sleep(sleepTimeInMillis);
        return null;
    }
}
