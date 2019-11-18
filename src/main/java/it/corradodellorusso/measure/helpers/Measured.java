package it.corradodellorusso.measure.helpers;

import java.time.Duration;

/**
 * Interface for things that could be measured.
 */
public interface Measured {

    long toSeconds();

    long toMillis();

    long toNanos();

    Duration getDuration();

}
