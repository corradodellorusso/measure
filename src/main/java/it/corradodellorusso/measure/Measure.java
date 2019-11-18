package it.corradodellorusso.measure;

import it.corradodellorusso.measure.helpers.MeasuredCallable;
import it.corradodellorusso.measure.helpers.MeasuredRunnable;
import it.corradodellorusso.measure.unit.Unit;

import java.time.Duration;
import java.time.Instant;
import java.util.EnumSet;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Base class for the measurement!
 *
 * @author Corrado dello Russo
 */
public class Measure {

    /*
    Should not be instantiated.
     */
    private Measure() {
    }

    /**
     * Measures a {@link Runnable} returning a {@link Duration}.
     * Good to use in place with lambda.
     *
     * @param toMeasure the runnable to measure.
     * @return measured runnable duration.
     */
    public static Duration measure(Runnable toMeasure) {
        return doMeasure(toMeasure);
    }

    /**
     * Measures a {@link Runnable} returning duration in millis.
     * Good to use in place with lambda.
     *
     * @param toMeasure the runnable to measure.
     * @return measured runnable duration in millis.
     */
    public static long measureMillis(Runnable toMeasure) {
        return doMeasure(toMeasure).toMillis();
    }

    /**
     * Measures a {@link Runnable} returning duration in the specified {@link Unit}.
     * Good to use in place with lambda.
     *
     * @param unit      measurement unit.
     * @param toMeasure the runnable to measure.
     * @return measured runnable duration in specified unit.
     */
    public static long measure(Unit unit, Runnable toMeasure) {

        if (Objects.isNull(unit)) {
            throw new MeasureUnitException("Unit must be one of " + EnumSet.allOf(Unit.class) + ".");
        }

        Duration duration = doMeasure(toMeasure);
        switch (unit) {
            case NANOS:
                return duration.toNanos();
            case MILLIS:
                return duration.toMillis();
            case SECONDS:
                return duration.toMillis() / 1000;
            default:
                throw new MeasureUnitException("Unit must be one of " + EnumSet.allOf(Unit.class) + ".");
        }
    }

    /**
     * Creates measurable {@link Runnable} out of provided one.
     *
     * @param runnable the runnable to measure.
     * @return measurable runner.
     */
    public static MeasuredRunnable of(Runnable runnable) {
        return new MeasuredRunnable(runnable);
    }

    /**
     * Creates measurable {@link Callable} out of provided one.
     *
     * @param callable the callable to measure.
     * @return measurable callable.
     */
    public static <T> MeasuredCallable<T> of(Callable<T> callable) {
        return new MeasuredCallable<>(callable);
    }

    /*
    Core code.
     */
    static Duration doMeasure(Runnable toMeasure) {

        if (Objects.isNull(toMeasure)) {
            throw new MeasureException("The code to measure must not be null");
        }

        Instant start = Instant.now();
        toMeasure.run();
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

}
