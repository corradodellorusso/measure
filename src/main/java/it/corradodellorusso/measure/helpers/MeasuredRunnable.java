package it.corradodellorusso.measure.helpers;

import it.corradodellorusso.measure.Measure;
import it.corradodellorusso.measure.MeasureException;

import java.time.Duration;
import java.util.Objects;

public class MeasuredRunnable implements Runnable, Measured {

    private Runnable toRun;
    private Duration duration;
    private boolean hasRun;

    public MeasuredRunnable(Runnable toRun) {
        if (Objects.isNull(toRun)) {
            throw new MeasureException("The code to measure must not be null");
        }
        this.toRun = toRun;
        this.hasRun = false;
    }

    @Override
    public void run() {
        this.duration = Measure.measure(toRun);
        this.hasRun = true;
    }

    @Override
    public long toSeconds() {
        if (hasRun) {
            return duration.toMillis() / 1000;
        }
        return -1;
    }

    @Override
    public long toMillis() {
        if (hasRun) {
            return duration.toMillis();
        }
        return -1;
    }

    @Override
    public long toNanos() {
        if (hasRun) {
            return duration.toNanos();
        }
        return -1;
    }

    @Override
    public Duration getDuration() {
        if (hasRun) {
            return duration;
        }
        return null;
    }
}
