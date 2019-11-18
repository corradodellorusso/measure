package it.corradodellorusso.measure.helpers;

import it.corradodellorusso.measure.MeasureException;

import java.util.concurrent.Callable;

/**
 * Adapter for internal use.
 * Maps every {@link Exception} to a {@link MeasureException} wrapping actual exception.
 *
 * @param <V> the return type.
 */
class CallableToRunnable<V> implements Runnable {

    private Callable<V> toRun;
    private V result;

    public CallableToRunnable(Callable<V> toRun) {
        this.toRun = toRun;
    }

    @Override
    public void run() {
        try {
            result = toRun.call();
        } catch (Exception e) {
            throw new MeasureException("Inner callable has thrown an exception.", e);
        }
    }

    public V getResult() {
        return result;
    }

}
