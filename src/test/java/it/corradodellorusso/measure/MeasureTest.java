package it.corradodellorusso.measure;

import it.corradodellorusso.measure.helpers.MeasuredCallable;
import it.corradodellorusso.measure.helpers.MeasuredRunnable;
import it.corradodellorusso.measure.unit.Unit;
import it.corradodellorusso.measure.util.WaitingCallable;
import it.corradodellorusso.measure.util.WaitingRunnable;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;


public class MeasureTest {

    @Test(expected = MeasureException.class)
    public void shouldRejectMissingRunnable() {
        Measure.doMeasure(null);
    }

    @Test(expected = MeasureException.class)
    public void shouldRejectMissingRunnableWithUnit() {
        Measure.measure(Unit.MILLIS, null);
    }

    @Test(expected = MeasureUnitException.class)
    public void shouldRejectMissingUnit() {
        Measure.measure(null, () -> {
        });
    }

    @Test()
    public void shouldMeasureMillis() {
        long millis = Measure.measureMillis(new WaitingRunnable(1L));
        assertThat(millis, greaterThanOrEqualTo(1L));
    }

    @Test
    public void shouldProduceRunnable() {
        Runnable runnable = new WaitingRunnable(1);
        MeasuredRunnable measured = Measure.of(runnable);
        assertNull(measured.getDuration());
        assertEquals(measured.toMillis(), -1);
        assertEquals(measured.toNanos(), -1);
        assertEquals(measured.toSeconds(), -1);
        measured.run();
        assertNotNull(measured.getDuration());
        assertThat(measured.toMillis(), greaterThanOrEqualTo(1L));       assertThat(measured.toNanos(), greaterThanOrEqualTo(1L));
        assertThat(measured.toNanos(), greaterThanOrEqualTo(0L));
    }

    @Test
    public void shouldProduceCallable() throws Exception {
        Callable<Void> callable = new WaitingCallable(1);
        MeasuredCallable<Void> measured = Measure.of(callable);
        assertNull(measured.getDuration());
        assertEquals(measured.toMillis(), -1);
        assertEquals(measured.toNanos(), -1);
        assertEquals(measured.toSeconds(), -1);
        measured.call();
        assertNotNull(measured.getDuration());
        assertThat(measured.toMillis(), greaterThanOrEqualTo(1L));
        assertThat(measured.toNanos(), greaterThanOrEqualTo(1L));
        assertThat(measured.toNanos(), greaterThanOrEqualTo(0L));
    }

}
