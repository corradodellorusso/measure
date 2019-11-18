package it.corradodellorusso.measure.helpers;

import it.corradodellorusso.measure.MeasureException;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;

public class MeasuredCallableTest {

    @Test
    public void shouldRunSmoothly() throws Exception {
        Callable<Long> callable = () -> 1L;
        MeasuredCallable<Long> measured = new MeasuredCallable<>(callable);
        long result = measured.call();
        Assert.assertNotNull("Duration should not be null", measured.getDuration());
        Assert.assertEquals("Result should be the exact", 1L, result);
    }

    @Test(expected = MeasureException.class)
    public void shouldThrowException() throws Exception {
        Callable<Long> callable = () -> {
            throw new Exception("Exception!");
        };
        MeasuredCallable<Long> measured = new MeasuredCallable<>(callable);
        measured.call();
    }

    @Test()
    public void shouldThrowExceptionAndHaveNullDuration() {
        long exceptionThrownCounter = 0;
        Callable<Long> callable = () -> {
            throw new Exception("Exception!");
        };
        MeasuredCallable<Long> measured = new MeasuredCallable<>(callable);
        try {
            measured.call();
        } catch (Exception e) {
            exceptionThrownCounter++;
        }
        Assert.assertEquals(1, exceptionThrownCounter);
        Assert.assertNull(measured.getDuration());
    }

    @Test(expected = MeasureException.class)
    public void shouldThrowExceptionWhenNull() throws Exception {
        MeasuredCallable<Long> measured = new MeasuredCallable<>(null);
    }


}
