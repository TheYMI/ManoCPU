package Tests.Emulator.Components;

import Emulator.Components.Bus;
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
 * Time: 12:29
 * Description:
 * ***********************
 */

public class BusTest {

    Bus   tester;
    Value value;

    @Before
    public void setUp() {
        tester = new Bus(0, "Tester");
        value  = new Value(Constants.DATA_REGISTER_SIZE);
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
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

        value.set_content(255);
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

        value.set_content("AAAA");
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

    }

    @Test
    public void testGet_value() throws Exception {

        System.out.println("~ testGet_value ~");

        value.set_content(0);
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_value().toString());
        assertEquals("0", tester.get_value(5).toString());
        System.out.println("Bit 5:              ^      | Tester[5]: " + tester.get_value(5).toString());
        assertEquals("0000", tester.get_value(9, 12).toString());
        System.out.println("Bits 9-12:   ^^^^          | Tester[9-12]: " + tester.get_value(9, 12).toString());

        value.set_content(255);
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_value().toString());
        assertEquals("1", tester.get_value(7).toString());
        System.out.println("Bit 7:            ^        | Tester[7]: " + tester.get_value(7).toString());
        assertEquals("0011111", tester.get_value(3, 9).toString());
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + tester.get_value(3, 9).toString());

        value.set_content("AAAA");
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_value().toString());
        assertEquals("1", tester.get_value(13).toString());
        System.out.println("Bit 13:     ^              | Tester[13]: " + tester.get_value(13).toString());
        assertEquals("0101010", tester.get_value(0, 6).toString());
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + tester.get_value(0, 6).toString());

    }

    @Test
    public void testGet_row() throws Exception {

        System.out.println("~ testGet_row ~");

        value.set_content(0);
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_row(8).toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

        value.set_content(255);
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_row(19).toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

        value.set_content("AAAA");
        tester.set_value(value);
        assertEquals(value.toString(), tester.get_row(587).toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + tester.get_value().toString());

    }

    @Test
    public void testEvaluateAsBoolean() throws Exception {

        System.out.println("~ testEvaluateAsBoolean ~");

        value.set_content(0);
        tester.set_value(value);
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.evaluateAsBoolean());
        assertFalse(tester.evaluateAsBoolean(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + tester.evaluateAsBoolean(5));
        assertFalse(tester.evaluateAsBoolean(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + tester.evaluateAsBoolean(3, 9));

        value.set_content(255);
        tester.set_value(value);
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.evaluateAsBoolean());
        assertFalse(tester.evaluateAsBoolean(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + tester.evaluateAsBoolean(8));
        assertFalse(tester.evaluateAsBoolean(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + tester.evaluateAsBoolean(9, 14));

        value.set_content("AAAA");
        tester.set_value(value);
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.evaluateAsBoolean());
        assertTrue(tester.evaluateAsBoolean(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + tester.evaluateAsBoolean(13));
        assertTrue(tester.evaluateAsBoolean(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + tester.evaluateAsBoolean(0, 6));

    }

    @Test
    public void testGet_decimal() throws Exception {

        System.out.println("~ testGet_decimal ~");

        value.set_content(0);
        tester.set_value(value);
        assertEquals(0, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_decimal());
        assertEquals(0, tester.get_decimal(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + tester.get_decimal(5));
        assertEquals(0, tester.get_decimal(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + tester.get_decimal(9, 14));

        value.set_content(255);
        tester.set_value(value);
        assertEquals(255, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_decimal());
        assertEquals(0, tester.get_decimal(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + tester.get_decimal(8));
        assertEquals(31, tester.get_decimal(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + tester.get_decimal(3, 9));

        value.set_content("AAAA");
        tester.set_value(value);
        assertEquals(43690, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_decimal());
        assertEquals(1, tester.get_decimal(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + tester.get_decimal(13));
        assertEquals(42, tester.get_decimal(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + tester.get_decimal(0, 6));

    }
}