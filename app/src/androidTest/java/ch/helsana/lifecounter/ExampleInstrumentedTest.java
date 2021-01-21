package ch.helsana.lifecounter;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ch.helsana.lifecounter.Model.LifePoints;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ch.helsana.lifecounter", appContext.getPackageName());
    }


    @Test
    public void subtractLP(){
        LifePoints lifePoints = LifePoints.getSingletonInstance();
        lifePoints.resetLifePoints();
        lifePoints.modifyLifePoints(-1000);
        assertEquals(7000, lifePoints.getLp());
    }

    @Test
    public void subtractLPBelowZero(){
        LifePoints lifePoints = LifePoints.getSingletonInstance();
        lifePoints.resetLifePoints();
        lifePoints.modifyLifePoints(-10000);
        assertEquals(0, lifePoints.getLp());
    }

    @Test
    public void resetLP(){
        LifePoints lifePoints = LifePoints.getSingletonInstance();
        lifePoints.modifyLifePoints(-1000);
        lifePoints.resetLifePoints();
        assertEquals(8000, lifePoints.getLp());
    }

    @Test
    public void AddLifePoints(){
        LifePoints lifePoints = LifePoints.getSingletonInstance();
        lifePoints.resetLifePoints();
        lifePoints.modifyLifePoints(1000);
        assertEquals(9000, lifePoints.getLp());
    }
}