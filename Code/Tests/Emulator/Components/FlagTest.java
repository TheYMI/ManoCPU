package Tests.Emulator.Components;

import Emulator.Components.Flag;
import Emulator.Global.Constants;
import Emulator.Global.Value;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 27/07/13
 * Time: 12:27
 * Description:
 * ***********************
 */

public class FlagTest {

    Flag  tester;
    Value value;

    @Before
    public void setUp() {
        tester = new Flag(0, "Tester");
        value  = new Value(1);

        tester.enableWrite();
    }

    @After
    public void tearDown() {
        System.out.println("");
        tester = null;
        value  = null;
    }

    @Test
    public void testSet_value() throws Exception {

        System.out.println("~ testSet_value ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_value().toString());

        value.set_content(1);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_value().toString());

        value = new Value("F");
        tester.set_value(value);
        tester.update();
        assertEquals(new Value(1, 1).toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Value MSb: " + value.toString().charAt(0) + " | Tester: " + tester.get_value().toString());

    }

    @Test
    public void testGet_value() throws Exception {

        System.out.println("~ testGet_value ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_value().toString());

        value.set_content(1);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_value().toString());

        value = new Value("F");
        tester.set_value(value);
        tester.update();
        assertEquals(new Value(1, 1).toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Value MSb: " + value.toString().charAt(0) + " | Tester: " + tester.get_value().toString());

    }

    @Test
    public void testEvaluateAsBoolean() throws Exception {

        System.out.println("~ testEvaluateAsBoolean ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.evaluateAsBoolean());

        value.set_content(1);
        tester.set_value(value);
        tester.update();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.evaluateAsBoolean());

    }

    @Test
    public void testGet_decimal() throws Exception {

        System.out.println("~ testGet_decimal ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(0, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

        value.set_content(1);
        tester.set_value(value);
        tester.update();
        assertEquals(1, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

    }

    @Test
    public void testSet() throws Exception {

        System.out.println("~ testSet ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(0, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

        System.out.println("Setting");
        tester.set();
        tester.update();

        assertEquals(1, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

    }

    @Test
    public void testClear() throws Exception {

        System.out.println("~ testClear ~");

        value.set_content(1);
        tester.set_value(value);
        tester.update();
        assertEquals(1, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

        System.out.println("Clearing");
        tester.clear();
        tester.update();

        assertEquals(0, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

    }

    @Test
    public void testComplement() throws Exception {

        System.out.println("~ testComplement ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(0, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

        System.out.println("Complement");
        tester.complement();
        tester.update();

        assertEquals(1, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

        System.out.println("Complement");
        tester.complement();
        tester.update();

        assertEquals(0, tester.get_decimal());
        System.out.println("Value: " + value.toString().charAt(0) + " | Tester: " + tester.get_decimal());

    }

    @Test
    public void testChanged() throws Exception {

        System.out.println("~ testComplement ~");

        value.set_content(1);
        tester.set_value(value);
        tester.update();
        System.out.println("Tester: " + tester.get_decimal());
        if (tester.changed()) {
            System.out.println("Changed");
            assertTrue(tester.changed());
        } else {
            System.out.println("Didn't change");
            assertFalse(tester.changed());
        }

        System.out.println("Complement");
        tester.complement();
        tester.update();

        System.out.println("Tester: " + tester.get_decimal());
        if (tester.changed()) {
            System.out.println("Changed");
            assertTrue(tester.changed());
        } else {
            System.out.println("Didn't change");
            assertFalse(tester.changed());
        }

        System.out.println("Complement");
        tester.complement();
        tester.update();

        System.out.println("Tester: " + tester.get_decimal());
        if (tester.changed()) {
            System.out.println("Changed");
            assertTrue(tester.changed());
        } else {
            System.out.println("Didn't change");
            assertFalse(tester.changed());
        }


    }

    @Test
    public void testUpdate() throws Exception {

        System.out.println("~ testDisableWrite ~");

        value.set_content(1);
        tester.set_value(value);
        assertFalse(value.toString().equals(tester.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());
        System.out.println("Updating");
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

    }
}