package Tests.Emulator.Components;

import Emulator.Components.DataComponent;
import Emulator.Components.ALU;
import Emulator.Components.Flag;
import Emulator.Global.Constants;
import Emulator.Global.Value;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 27/07/13
 * Time: 12:30
 * Description:
 * ***********************
 */

public class ALUTest {

    static Flag E;

    ALU           tester;
    DataComponent input0, input1, output;
    Value         value;

    @BeforeClass
    static public void setUpClass() {
        E = new Flag(0, "E");
    }

    @AfterClass
    static public void tearDownClass() {
        E = null;
    }

    @Before
    public void setUp() {
        value  = new Value(Constants.DATA_REGISTER_SIZE);
        tester = new ALU(0, "Tester", E);

        input0 = tester.get_input0();
        input1 = tester.get_input1();
        output = tester.get_output();

        input0.enableWrite();
        input1.enableWrite();
    }

    @After
    public void tearDown() {
        System.out.println("");
        value  = null;
        input0 = null;
        input1 = null;
        output = null;
        tester = null;
    }

    @Test
    public void testInputSet_value() throws Exception {

        System.out.println("~ testInputSet_value ~");

        value.set_content(0);
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

        value.set_content(255);
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

        value.set_content("AAAA");
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

    }

    @Test
    public void testInputGet_value() throws Exception {

        System.out.println("~ testInputGet_value ~");

        value.set_content(0);
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.get_value().toString());
        assertEquals("0", input0.get_value(5).toString());
        System.out.println("Bit 5:              ^      | Tester[5]: " + input0.get_value(5).toString());
        assertEquals("0000", input0.get_value(9, 12).toString());
        System.out.println("Bits 9-12:   ^^^^          | Tester[9-12]: " + input0.get_value(9, 12).toString());

        value.set_content(255);
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.get_value().toString());
        assertEquals("1", input0.get_value(7).toString());
        System.out.println("Bit 7:            ^        | Tester[7]: " + input0.get_value(7).toString());
        assertEquals("0011111", input0.get_value(3, 9).toString());
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + input0.get_value(3, 9).toString());

        value.set_content("AAAA");
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.get_value().toString());
        assertEquals("1", input0.get_value(13).toString());
        System.out.println("Bit 13:     ^              | Tester[13]: " + input0.get_value(13).toString());
        assertEquals("0101010", input0.get_value(0, 6).toString());
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + input0.get_value(0, 6).toString());

    }

    @Test
    public void testInputEvaluateAsBoolean() throws Exception {

        System.out.println("~ testInputEvaluateAsBoolean ~");

        value.set_content(0);
        input0.set_value(value);
        assertFalse(input0.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.evaluateAsBoolean());
        assertFalse(input0.evaluateAsBoolean(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + input0.evaluateAsBoolean(5));
        assertFalse(input0.evaluateAsBoolean(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + input0.evaluateAsBoolean(3, 9));

        value.set_content(255);
        input0.set_value(value);
        assertTrue(input0.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.evaluateAsBoolean());
        assertFalse(input0.evaluateAsBoolean(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + input0.evaluateAsBoolean(8));
        assertFalse(input0.evaluateAsBoolean(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + input0.evaluateAsBoolean(9, 14));

        value.set_content("AAAA");
        input0.set_value(value);
        assertTrue(input0.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.evaluateAsBoolean());
        assertTrue(input0.evaluateAsBoolean(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + input0.evaluateAsBoolean(13));
        assertTrue(input0.evaluateAsBoolean(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + input0.evaluateAsBoolean(0, 6));

    }

    @Test
    public void testInputGet_decimal() throws Exception {

        System.out.println("~ testInputGet_decimal ~");

        value.set_content(0);
        input0.set_value(value);
        assertEquals(0, input0.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.get_decimal());
        assertEquals(0, input0.get_decimal(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + input0.get_decimal(5));
        assertEquals(0, input0.get_decimal(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + input0.get_decimal(9, 14));

        value.set_content(255);
        input0.set_value(value);
        assertEquals(255, input0.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.get_decimal());
        assertEquals(0, input0.get_decimal(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + input0.get_decimal(8));
        assertEquals(31, input0.get_decimal(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + input0.get_decimal(3, 9));

        value.set_content("AAAA");
        input0.set_value(value);
        assertEquals(43690, input0.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + input0.get_decimal());
        assertEquals(1, input0.get_decimal(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + input0.get_decimal(13));
        assertEquals(42, input0.get_decimal(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + input0.get_decimal(0, 6));

    }

    @Test
    public void testInputEnableWrite() throws Exception {

        System.out.println("~ testInputEnableWrite ~");

        input0.disableWrite();

        value.set_content("AAAA");
        input0.set_value(value);
        assertFalse(value.toString().equals(input0.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

        System.out.println("Enabling Write");
        input0.enableWrite();

        value.set_content("AAAA");
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());
    }

    @Test
    public void testInputDisableWrite() throws Exception {

        System.out.println("~ testInputDisableWrite ~");

        value.set_content("AAAA");
        input0.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

        System.out.println("Disabling Write");
        input0.disableWrite();

        value.set_content("F0F0");
        input0.set_value(value);
        assertFalse(value.toString().equals(input0.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

    }

    @Test
    public void testInputIsWritable() throws Exception {

        System.out.println("~ testInputIsWritable ~");

        value.set_content("AAAA");
        input0.set_value(value);
        if (input0.isWritable()) {
            System.out.println("Write is enabled");
            assertEquals(value.toString(), input0.get_value().toString());
        } else {
            System.out.println("Write is disabled");
            assertFalse(value.toString().equals(input0.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

        System.out.println("Disabling Write");
        input0.disableWrite();

        value.set_content("F0F0");
        input0.set_value(value);
        if (input0.isWritable()) {
            System.out.println("Write is enabled");
            assertEquals(value.toString(), input0.get_value().toString());
        } else {
            System.out.println("Write is disabled");
            assertFalse(value.toString().equals(input0.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

    }

    @Test
    public void testOutputSet_value() throws Exception {

        System.out.println("~ testOutputSet_value ~");

        value.set_content(0);
        output.set_value(value);
        assertEquals(value.toString(), output.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + output.get_value().toString());

        value.set_content(255);
        output.set_value(value);
        assertEquals(value.toString(), output.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + output.get_value().toString());

        value.set_content("AAAA");
        output.set_value(value);
        assertEquals(value.toString(), output.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester: " + output.get_value().toString());

    }

    @Test
    public void testOutputGet_value() throws Exception {

        System.out.println("~ testOutputGet_value ~");

        value.set_content(0);
        output.set_value(value);
        assertEquals(value.toString(), output.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.get_value().toString());
        assertEquals("0", output.get_value(5).toString());
        System.out.println("Bit 5:              ^      | Tester[5]: " + output.get_value(5).toString());
        assertEquals("0000", output.get_value(9, 12).toString());
        System.out.println("Bits 9-12:   ^^^^          | Tester[9-12]: " + output.get_value(9, 12).toString());

        value.set_content(255);
        output.set_value(value);
        assertEquals(value.toString(), output.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.get_value().toString());
        assertEquals("1", output.get_value(7).toString());
        System.out.println("Bit 7:            ^        | Tester[7]: " + output.get_value(7).toString());
        assertEquals("0011111", output.get_value(3, 9).toString());
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + output.get_value(3, 9).toString());

        value.set_content("AAAA");
        output.set_value(value);
        assertEquals(value.toString(), output.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.get_value().toString());
        assertEquals("1", output.get_value(13).toString());
        System.out.println("Bit 13:     ^              | Tester[13]: " + output.get_value(13).toString());
        assertEquals("0101010", output.get_value(0, 6).toString());
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + output.get_value(0, 6).toString());

    }

    @Test
    public void testOutputEvaluateAsBoolean() throws Exception {

        System.out.println("~ testOutputEvaluateAsBoolean ~");

        value.set_content(0);
        output.set_value(value);
        assertFalse(output.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.evaluateAsBoolean());
        assertFalse(output.evaluateAsBoolean(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + output.evaluateAsBoolean(5));
        assertFalse(output.evaluateAsBoolean(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + output.evaluateAsBoolean(3, 9));

        value.set_content(255);
        output.set_value(value);
        assertTrue(output.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.evaluateAsBoolean());
        assertFalse(output.evaluateAsBoolean(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + output.evaluateAsBoolean(8));
        assertFalse(output.evaluateAsBoolean(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + output.evaluateAsBoolean(9, 14));

        value.set_content("AAAA");
        output.set_value(value);
        assertTrue(output.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.evaluateAsBoolean());
        assertTrue(output.evaluateAsBoolean(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + output.evaluateAsBoolean(13));
        assertTrue(output.evaluateAsBoolean(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + output.evaluateAsBoolean(0, 6));

    }

    @Test
    public void testOutputGet_decimal() throws Exception {

        System.out.println("~ testOutputGet_decimal ~");

        value.set_content(0);
        output.set_value(value);
        assertEquals(0, output.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.get_decimal());
        assertEquals(0, output.get_decimal(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + output.get_decimal(5));
        assertEquals(0, output.get_decimal(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + output.get_decimal(9, 14));

        value.set_content(255);
        output.set_value(value);
        assertEquals(255, output.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.get_decimal());
        assertEquals(0, output.get_decimal(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + output.get_decimal(8));
        assertEquals(31, output.get_decimal(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + output.get_decimal(3, 9));

        value.set_content("AAAA");
        output.set_value(value);
        assertEquals(43690, output.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + output.get_decimal());
        assertEquals(1, output.get_decimal(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + output.get_decimal(13));
        assertEquals(42, output.get_decimal(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + output.get_decimal(0, 6));

    }

    @Test
    public void testGet_value() throws Exception {

        System.out.println("~ testGet_value ~");

        value.set_content(0);
        output.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_value().toString());
        assertEquals("0", tester.get_value(5).toString());
        System.out.println("Bit 5:              ^      | Tester[5]: " + tester.get_value(5).toString());
        assertEquals("0000", tester.get_value(9, 12).toString());
        System.out.println("Bits 9-12:   ^^^^          | Tester[9-12]: " + tester.get_value(9, 12).toString());

        value.set_content(255);
        output.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_value().toString());
        assertEquals("1", tester.get_value(7).toString());
        System.out.println("Bit 7:            ^        | Tester[7]: " + tester.get_value(7).toString());
        assertEquals("0011111", tester.get_value(3, 9).toString());
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + tester.get_value(3, 9).toString());

        value.set_content("AAAA");
        output.set_value(value);
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_value().toString());
        assertEquals("1", tester.get_value(13).toString());
        System.out.println("Bit 13:     ^              | Tester[13]: " + tester.get_value(13).toString());
        assertEquals("0101010", tester.get_value(0, 6).toString());
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + tester.get_value(0, 6).toString());

    }

    @Test
    public void testGet_input0() throws Exception {

        System.out.println("~ testGet_input0 ~");

        System.out.println("Getting input0");
        assertNotNull(tester.get_input0());

    }

    @Test
    public void testGet_input1() throws Exception {


        System.out.println("~ testGet_input1 ~");

        System.out.println("Getting input1");
        assertNotNull(tester.get_input1());

    }

    @Test
    public void testGet_output() throws Exception {

        System.out.println("~ testGet_output ~");

        System.out.println("Getting output");
        assertNotNull(tester.get_output());

    }

    @Test
    public void testEvaluateAsBoolean() throws Exception {

        System.out.println("~ testEvaluateAsBoolean ~");

        value.set_content(0);
        output.set_value(value);
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.evaluateAsBoolean());
        assertFalse(tester.evaluateAsBoolean(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + tester.evaluateAsBoolean(5));
        assertFalse(tester.evaluateAsBoolean(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + tester.evaluateAsBoolean(3, 9));

        value.set_content(255);
        output.set_value(value);
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.evaluateAsBoolean());
        assertFalse(tester.evaluateAsBoolean(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + tester.evaluateAsBoolean(8));
        assertFalse(tester.evaluateAsBoolean(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + tester.evaluateAsBoolean(9, 14));

        value.set_content("AAAA");
        output.set_value(value);
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
        output.set_value(value);
        assertEquals(0, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_decimal());
        assertEquals(0, tester.get_decimal(5));
        System.out.println("Bit 5:              ^      | Tester[5]: " + tester.get_decimal(5));
        assertEquals(0, tester.get_decimal(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[9-14]: " + tester.get_decimal(9, 14));

        value.set_content(255);
        output.set_value(value);
        assertEquals(255, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_decimal());
        assertEquals(0, tester.get_decimal(8));
        System.out.println("Bit 8:           ^         | Tester[8]: " + tester.get_decimal(8));
        assertEquals(31, tester.get_decimal(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[3-9]: " + tester.get_decimal(3, 9));

        value.set_content("AAAA");
        output.set_value(value);
        assertEquals(43690, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester: " + tester.get_decimal());
        assertEquals(1, tester.get_decimal(13));
        System.out.println("Bit 13:     ^              | Tester[13]: " + tester.get_decimal(13));
        assertEquals(42, tester.get_decimal(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[0-6]: " + tester.get_decimal(0, 6));

    }

    @Test
    public void testEnableWrite0() throws Exception {

        System.out.println("~ testEnableWrite0 ~");

        input0.disableWrite();
        input1.disableWrite();

        value.set_content("AAAA");
        input0.set_value(value);
        input1.set_value(value);
        assertFalse(value.toString().equals(input0.get_value().toString()));
        assertFalse(value.toString().equals(input1.get_value().toString()));
        System.out.println("Input 0: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Input 1: " + value.toString() + " | Input 1: " + input1.get_value().toString());

        System.out.println("Enabling Write");
        tester.enableWrite0();

        value.set_content("AAAA");
        input0.set_value(value);
        input1.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        assertFalse(value.toString().equals(input1.get_value().toString()));
        System.out.println("Input 0: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Input 1: " + value.toString() + " | Input 1: " + input1.get_value().toString());

    }

    @Test
    public void testEnableWrite1() throws Exception {

        System.out.println("~ testEnableWrite1 ~");

        input0.disableWrite();
        input1.disableWrite();

        value.set_content("AAAA");
        input0.set_value(value);
        input1.set_value(value);
        assertFalse(value.toString().equals(input0.get_value().toString()));
        assertFalse(value.toString().equals(input1.get_value().toString()));
        System.out.println("Input 0: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Input 1: " + value.toString() + " | Input 1: " + input1.get_value().toString());

        System.out.println("Enabling Write");
        tester.enableWrite1();

        value.set_content("AAAA");
        input0.set_value(value);
        input1.set_value(value);
        assertEquals(value.toString(), input1.get_value().toString());
        assertFalse(value.toString().equals(input0.get_value().toString()));
        System.out.println("Input 0: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Input 1: " + value.toString() + " | Input 1: " + input1.get_value().toString());

    }

    @Test
    public void testDisableWrite0() throws Exception {

        System.out.println("~ testDisableWrite0 ~");

        value.set_content("AAAA");
        input0.set_value(value);
        input1.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        assertEquals(value.toString(), input1.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Input 1: " + input1.get_value().toString());

        System.out.println("Disabling Write");
        tester.disableWrite0();

        value.set_content("F0F0");
        input0.set_value(value);
        input1.set_value(value);
        assertEquals(value.toString(), input1.get_value().toString());
        assertFalse(value.toString().equals(input0.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Input 1: " + input1.get_value().toString());

    }

    @Test
    public void testDisableWrite1() throws Exception {

        System.out.println("~ testDisableWrite1 ~");

        value.set_content("AAAA");
        input0.set_value(value);
        input1.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        assertEquals(value.toString(), input1.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Input 1: " + input1.get_value().toString());

        System.out.println("Disabling Write");
        tester.disableWrite1();

        value.set_content("F0F0");
        input0.set_value(value);
        input1.set_value(value);
        assertEquals(value.toString(), input0.get_value().toString());
        assertFalse(value.toString().equals(input1.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Input 0: " + input0.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Input 1: " + input1.get_value().toString());

    }

    @Test
    public void testIsWritable0() throws Exception {

        System.out.println("~ testIsWritable0 ~");

        value.set_content("AAAA");
        input0.set_value(value);
        if (tester.isWritable0()) {
            System.out.println("Write is enabled on input 0");
            assertEquals(value.toString(), input0.get_value().toString());
        } else {
            System.out.println("Write is disabled on input 0");
            assertFalse(value.toString().equals(input0.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

        System.out.println("Disabling Write on input 0");
        input0.disableWrite();

        value.set_content("F0F0");
        input0.set_value(value);
        if (tester.isWritable0()) {
            System.out.println("Write is enabled on input 0");
            assertEquals(value.toString(), input0.get_value().toString());
        } else {
            System.out.println("Write is disabled on input 0");
            assertFalse(value.toString().equals(input0.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester: " + input0.get_value().toString());

    }

    @Test
    public void testIsWritable1() throws Exception {

        System.out.println("~ testIsWritable1 ~");

        value.set_content("AAAA");
        input1.set_value(value);
        input1.update();
        if (tester.isWritable1()) {
            System.out.println("Write is enabled on input 1");
            assertEquals(value.toString(), input1.get_value().toString());
        } else {
            System.out.println("Write is disabled on input 1");
            assertFalse(value.toString().equals(input1.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester: " + input1.get_value().toString());

        System.out.println("Disabling Write on input 1");
        input1.disableWrite();

        value.set_content("F0F0");
        input1.set_value(value);
        input1.update();
        if (tester.isWritable1()) {
            System.out.println("Write is enabled on input 1");
            assertEquals(value.toString(), input1.get_value().toString());
        } else {
            System.out.println("Write is disabled on input 1");
            assertFalse(value.toString().equals(input1.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester: " + input1.get_value().toString());

    }

    @Test
    public void testPassThrough0() throws Exception {

        System.out.println("~ testPassThrough0 ~");

        value.set_content(0);
        input0.set_value(value);
        tester.passThrough0();
        assertEquals(input0.get_value().toString(), tester.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + input0.get_value().toString());

        value.set_content(255);
        input0.set_value(value);
        tester.passThrough0();
        assertEquals(input0.get_value().toString(), tester.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + input0.get_value().toString());

        value.set_content("AAAA");
        input0.set_value(value);
        tester.passThrough0();
        assertEquals(input0.get_value().toString(), tester.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + input0.get_value().toString());

    }

    @Test
    public void testPassThrough1() throws Exception {

        System.out.println("~ testPassThrough1 ~");

        value.set_content(0);
        input1.set_value(value);
        tester.passThrough1();
        assertEquals(input1.get_value().toString(), tester.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + input1.get_value().toString());

        value.set_content(255);
        input1.set_value(value);
        tester.passThrough1();
        assertEquals(input1.get_value().toString(), tester.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + input1.get_value().toString());

        value.set_content("AAAA");
        input1.set_value(value);
        tester.passThrough1();
        assertEquals(input1.get_value().toString(), tester.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + input1.get_value().toString());

    }

    @Test
    public void testSum() throws Exception {

        System.out.println("~ testSum ~");

        value.set_content(43);
        input0.set_value(value);
        value.set_content(78);
        input1.set_value(value);
        tester.sum();
        E.update();
        assertEquals(121, output.get_decimal());
        assertFalse(E.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content("FFF8");
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.sum();
        E.update();
        assertEquals("1111111111111111", output.get_value().toString());
        assertFalse(E.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content("FFF8");
        input0.set_value(value);
        value.set_content(9);
        input1.set_value(value);
        tester.sum();
        E.update();
        assertEquals(1, output.get_decimal());
        assertTrue(E.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content("FFFF");
        input0.set_value(value);
        value.set_content("FFFF");
        input1.set_value(value);
        tester.sum();
        E.update();
        assertEquals("1111111111111110", output.get_value().toString());
        assertTrue(E.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

    }

    @Test
    public void testSubtract() throws Exception {

        System.out.println("~ testSubtract ~");

        value.set_content(2000);
        input0.set_value(value);
        value.set_content(114);
        input1.set_value(value);
        tester.subtract();
        assertEquals(1886, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + value.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content(43);
        input0.set_value(value);
        value.set_content(78);
        input1.set_value(value);
        tester.subtract();
        assertEquals("1111111111011101", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + value.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content("FFFE");
        input0.set_value(value);
        value.set_content("FFFF");
        input1.set_value(value);
        tester.subtract();
        assertEquals("1111111111111111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + value.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

    }

    @Test
    public void testMultiply() throws Exception {

        System.out.println("~ testMultiply ~");

        value.set_content(4);
        input0.set_value(value);
        value.set_content(3);
        input1.set_value(value);
        tester.multiply();
        assertEquals(12, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content(43);
        input0.set_value(value);
        value.set_content(78);
        input1.set_value(value);
        tester.multiply();
        assertEquals(3354, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content("92");
        input0.set_value(value);
        value.set_content("1371");
        input1.set_value(value);
        tester.multiply();
        assertEquals("0001011001110010", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

    }

    @Test
    public void testDivide() throws Exception {

        System.out.println("~ testDivide ~");

        value.set_content(15);
        input0.set_value(value);
        value.set_content(3);
        input1.set_value(value);
        tester.divide();
        assertEquals(5, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content(31);
        input0.set_value(value);
        value.set_content(12);
        input1.set_value(value);
        tester.divide();
        assertEquals(2, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content(58);
        input0.set_value(value);
        value.set_content(101);
        input1.set_value(value);
        tester.divide();
        assertEquals(0, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

    }

    @Test
    public void testModulo() throws Exception {

        System.out.println("~ testModulo ~");

        value.set_content(15);
        input0.set_value(value);
        value.set_content(3);
        input1.set_value(value);
        tester.modulo();
        assertEquals(0, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content(31);
        input0.set_value(value);
        value.set_content(12);
        input1.set_value(value);
        tester.modulo();
        assertEquals(7, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

        value.set_content(58);
        input0.set_value(value);
        value.set_content(101);
        input1.set_value(value);
        tester.modulo();
        assertEquals(58, output.get_decimal());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.get_decimal() + " | E: " + E.get_decimal());

    }

    @Test
    public void testAnd() throws Exception {

        System.out.println("~ testAnd ~");

        value.set_content("FFFF");
        input0.set_value(value);
        value.set_content("F0F0");
        input1.set_value(value);
        tester.and();
        assertEquals("1111000011110000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("AAAA");
        input0.set_value(value);
        value.set_content("5555");
        input1.set_value(value);
        tester.and();
        assertEquals("0000000000000000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content(43251);
        input0.set_value(value);
        value.set_content("0FF0");
        input1.set_value(value);
        tester.and();
        assertEquals("0000100011110000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testOr() throws Exception {

        System.out.println("~ testOr ~");


        value.set_content("FFFF");
        input0.set_value(value);
        value.set_content("F0F0");
        input1.set_value(value);
        tester.or();
        assertEquals("1111111111111111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("AAAA");
        input0.set_value(value);
        value.set_content("5555");
        input1.set_value(value);
        tester.or();
        assertEquals("1111111111111111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content(43251);
        input0.set_value(value);
        value.set_content("0FF0");
        input1.set_value(value);
        tester.or();
        assertEquals("1010111111110011", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testXor() throws Exception {

        System.out.println("~ testXor ~");


        value.set_content("FFFF");
        input0.set_value(value);
        value.set_content("9999");
        input1.set_value(value);
        tester.xor();
        assertEquals("0110011001100110", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("AAAA");
        input0.set_value(value);
        value.set_content("5555");
        input1.set_value(value);
        tester.xor();
        assertEquals("1111111111111111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("BBBB");
        input0.set_value(value);
        value.set_content("BBBB");
        input1.set_value(value);
        tester.xor();
        assertEquals("0000000000000000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testComplement0() throws Exception {

        System.out.println("~ testComplement0 ~");

        value.set_content("FFFF");
        input0.set_value(value);
        tester.complement0();
        assertEquals("0000000000000000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("0000");
        input0.set_value(value);
        tester.complement0();
        assertEquals("1111111111111111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("CCCC");
        input0.set_value(value);
        tester.complement0();
        assertEquals("0011001100110011", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testComplement1() throws Exception {

        System.out.println("~ testComplement1 ~");

        value.set_content("FFFF");
        input1.set_value(value);
        tester.complement1();
        assertEquals("0000000000000000", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("0000");
        input1.set_value(value);
        tester.complement1();
        assertEquals("1111111111111111", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("CCCC");
        input1.set_value(value);
        tester.complement1();
        assertEquals("0011001100110011", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testEqual() throws Exception {

        System.out.println("~ testEqual ~");

        value.set_content(0);
        input0.set_value(value);
        input1.set_value(value);
        tester.equal();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(3);
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.equal();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

    }

    @Test
    public void testNotEqual() throws Exception {

        System.out.println("~ testNotEqual ~");

        value.set_content(0);
        input0.set_value(value);
        input1.set_value(value);
        tester.notEqual();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(3);
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.notEqual();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

    }

    @Test
    public void testGreaterThan() throws Exception {

        System.out.println("~ testGreaterThan ~");

        value.set_content(5);
        input0.set_value(value);
        value.set_content(2);
        input1.set_value(value);
        tester.greaterThan();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(7);
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.greaterThan();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(12);
        input0.set_value(value);
        value.set_content(43);
        input1.set_value(value);
        tester.greaterThan();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

    }

    @Test
    public void testLessThan() throws Exception {

        System.out.println("~ testLessThan ~");

        value.set_content(5);
        input0.set_value(value);
        value.set_content(93);
        input1.set_value(value);
        tester.lessThan();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(7);
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.lessThan();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(112);
        input0.set_value(value);
        value.set_content(43);
        input1.set_value(value);
        tester.lessThan();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

    }

    @Test
    public void testGreaterOrEqual() throws Exception {

        System.out.println("~ testGreaterOrEqual ~");

        value.set_content(5);
        input0.set_value(value);
        value.set_content(2);
        input1.set_value(value);
        tester.greaterOrEqual();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(7);
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.greaterOrEqual();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(12);
        input0.set_value(value);
        value.set_content(43);
        input1.set_value(value);
        tester.greaterOrEqual();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

    }

    @Test
    public void testLessOrEqual() throws Exception {

        System.out.println("~ testLessOrEqual ~");

        value.set_content(5);
        input0.set_value(value);
        value.set_content(93);
        input1.set_value(value);
        tester.lessOrEqual();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(7);
        input0.set_value(value);
        value.set_content(7);
        input1.set_value(value);
        tester.lessOrEqual();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

        value.set_content(112);
        input0.set_value(value);
        value.set_content(43);
        input1.set_value(value);
        tester.lessOrEqual();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Input 0: " + input0.get_decimal() + " | Input 1: " + input1.get_decimal() + " | Output: " + output.evaluateAsBoolean());

    }

    @Test
    public void testShiftLeft0() throws Exception {

        System.out.println("~ testShiftLeft0 ~");

        value.set_content("0F0F");
        input0.set_value(value);
        tester.shiftLeft0(1, false);
        assertEquals("0001111000011110", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftLeft0(1, false);
        assertEquals("0011110000111100", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftLeft0(1, false);
        assertEquals("0111100001111000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftLeft0(1, false);
        assertEquals("1111000011110000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("F0F0");
        input0.set_value(value);
        tester.shiftLeft0(1, true);
        assertEquals("1110000111100001", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftLeft0(1, true);
        assertEquals("1100001111000011", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftLeft0(1, true);
        assertEquals("1000011110000111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftLeft0(1, true);
        assertEquals("0000111100001111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testShiftLeft1() throws Exception {

        System.out.println("~ testShiftLeft1 ~");

        value.set_content("0F0F");
        input1.set_value(value);
        tester.shiftLeft1(1, false);
        assertEquals("0001111000011110", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftLeft1(1, false);
        assertEquals("0011110000111100", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftLeft1(1, false);
        assertEquals("0111100001111000", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftLeft1(1, false);
        assertEquals("1111000011110000", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("F0F0");
        input1.set_value(value);
        tester.shiftLeft1(1, true);
        assertEquals("1110000111100001", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftLeft1(1, true);
        assertEquals("1100001111000011", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftLeft1(1, true);
        assertEquals("1000011110000111", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftLeft1(1, true);
        assertEquals("0000111100001111", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testShiftRight0() throws Exception {

        System.out.println("~ testShiftRight0 ~");

        value.set_content("F0F0");
        input0.set_value(value);
        tester.shiftRight0(1, false);
        assertEquals("0111100001111000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftRight0(1, false);
        assertEquals("0011110000111100", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftRight0(1, false);
        assertEquals("0001111000011110", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftRight0(1, false);
        assertEquals("0000111100001111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("0F0F");
        input0.set_value(value);
        tester.shiftRight0(1, true);
        assertEquals("1000011110000111", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftRight0(1, true);
        assertEquals("1100001111000011", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftRight0(1, true);
        assertEquals("1110000111100001", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());
        input0.set_value(output.get_value());
        tester.shiftRight0(1, true);
        assertEquals("1111000011110000", output.get_value().toString());
        System.out.println("Input 0: " + input0.get_value().toString() + " | Output: " + output.get_value().toString());

    }

    @Test
    public void testShiftRight1() throws Exception {

        System.out.println("~ testShiftRight1 ~");

        value.set_content("F0F0");
        input1.set_value(value);
        tester.shiftRight1(1, false);
        assertEquals("0111100001111000", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftRight1(1, false);
        assertEquals("0011110000111100", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftRight1(1, false);
        assertEquals("0001111000011110", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftRight1(1, false);
        assertEquals("0000111100001111", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());

        value.set_content("0F0F");
        input1.set_value(value);
        tester.shiftRight1(1, true);
        assertEquals("1000011110000111", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftRight1(1, true);
        assertEquals("1100001111000011", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftRight1(1, true);
        assertEquals("1110000111100001", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());
        input1.set_value(output.get_value());
        tester.shiftRight1(1, true);
        assertEquals("1111000011110000", output.get_value().toString());
        System.out.println("Input 1: " + input1.get_value().toString() + " | Output: " + output.get_value().toString());


    }
}
