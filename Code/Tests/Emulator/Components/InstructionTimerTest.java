package Tests.Emulator.Components;

import Emulator.Components.InstructionTimer;
import Emulator.Global.Constants;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 27/07/13
 * Time: 12:26
 * Description:
 * ***********************
 */

public class InstructionTimerTest {

    InstructionTimer tester;

    @Before
    public void setUp() {
        tester = new InstructionTimer(0, "Tester");
    }

    @After
    public void tearDown() {
        System.out.println("");
        tester = null;
    }

    @Test
    public void testGet_currentCycle() throws Exception {

        System.out.println("~ testGet_currentCycle ~");

        assertEquals(0, tester.get_currentCycle());
        System.out.println("Current Cycle  = 0");
        tester.pulse();
        assertEquals(1, tester.get_currentCycle());
        System.out.println("Current Cycle  = 1");
        tester.pulse();
        tester.pulse();
        tester.pulse();
        assertEquals(4, tester.get_currentCycle());
        System.out.println("Current Cycle  = 4");

    }

    @Test
    public void testPulse() throws Exception {

        System.out.println("~ testPulse ~");

        for (int i = 0; i < Constants.TIMER_LIMIT * 2; ++i){
            if (i < Constants.TIMER_LIMIT) {
                assertEquals(i, tester.get_currentCycle());
                System.out.println("Cycle = " + tester.get_currentCycle() + " | i = " + i + " < " + Constants.TIMER_LIMIT);
            } else {
                assertFalse(tester.get_currentCycle() >= Constants.TIMER_LIMIT);
                System.out.println("Cycle = " + tester.get_currentCycle() + " | i = " + i + " >= " + Constants.TIMER_LIMIT);
            }
            tester.pulse();
        }

    }

    @Test
    public void testReset() throws Exception {

        System.out.println("~ testReset ~");

        for (int i = 0; i < Constants.TIMER_LIMIT / 2; ++i){
            System.out.println("Current Cycle  = " + i);
            tester.pulse();
        }

        System.out.println("Reset");
        tester.reset();
        assertEquals(0, tester.get_currentCycle());
        System.out.println("Current Cycle  = 0");

    }
}