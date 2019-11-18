package it.corradodellorusso.measure.helpers;

import it.corradodellorusso.measure.MeasureException;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;

public class MeasuredRunnableTest {

    @Test
    public void shouldRunSmoothly() throws Exception {
        Runnable callable = () -> {};
        MeasuredRunnable measured = new MeasuredRunnable(callable);
        measured.run();
        Assert.assertNotNull("Duration should not be null", measured.getDuration());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException() throws Exception {
        Runnable runnable = () -> {
            throw new RuntimeException("Exception!");
        };
        MeasuredRunnable measured = new MeasuredRunnable(runnable);
        measured.run();
    }

    @Test(expected = MeasureException.class)
    public void shouldThrowExceptionWhenNull() throws Exception {
        MeasuredRunnable measured = new MeasuredRunnable(null);
    }


}
