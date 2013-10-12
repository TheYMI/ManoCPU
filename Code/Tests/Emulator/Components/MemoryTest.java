package Tests.Emulator.Components;

import Emulator.Components.Memory;
import Emulator.Components.AddressRegister;
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
 * Time: 12:26
 * Description:
 * ***********************
 */

public class MemoryTest {

    static AddressRegister AR;
    static Memory          tester;

    Value  value;

    @BeforeClass
    static public void setUpClass() {
        AR     = new AddressRegister(0, "AR");
        tester = new Memory(0, "Tester", AR);

        tester.enableWrite();
        AR.enableWrite();
        AR.set_value(new Value("FFFF"));
        AR.update();
    }

    @AfterClass
    static public void tearDownClass() {
        tester = null;
        AR     = null;
    }

    @Before
    public void setUp() {
        value = new Value(Constants.DATA_REGISTER_SIZE);

        AR.increment();
        AR.update();
    }

    @After
    public void tearDown() {
        System.out.println("");
        value = null;
    }

    @Test
    public void testSet_value() throws Exception {

        System.out.println("~ testSet_value ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

        value.set_content(255);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

    }

    @Test
    public void testSet_metaCommand() throws Exception {

        System.out.println("~ testSet_metaCommand ~");

        String metaCommand = "Some assembly code!";

        tester.set_metaCommand(metaCommand);
        assertEquals(metaCommand, tester.get_metaCommand(AR.get_decimal()));
        System.out.println("Meta command: " + metaCommand + " | Tester[" + AR.get_decimal() + "]: " + tester.get_metaCommand(AR.get_decimal()));


    }

    @Test
    public void testGet_value() throws Exception {

        System.out.println("~ testGet_value ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());
        assertEquals("0", tester.get_value(5).toString());
        System.out.println("Bit 5:              ^      | Tester[" + AR.get_decimal() + "][5]: " + tester.get_value(5).toString());
        assertEquals("0000", tester.get_value(9, 12).toString());
        System.out.println("Bits 9-12:   ^^^^          | Tester[" + AR.get_decimal() + "][9-12]: " + tester.get_value(9, 12).toString());

        value.set_content(255);
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());
        assertEquals("1", tester.get_value(7).toString());
        System.out.println("Bit 7:            ^        | Tester[" + AR.get_decimal() + "][7]: " + tester.get_value(7).toString());
        assertEquals("0011111", tester.get_value(3, 9).toString());
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[" + AR.get_decimal() + "][3-9]: " + tester.get_value(3, 9).toString());

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());
        assertEquals("1", tester.get_value(13).toString());
        System.out.println("Bit 13:     ^              | Tester[" + AR.get_decimal() + "][13]: " + tester.get_value(13).toString());
        assertEquals("0101010", tester.get_value(0, 6).toString());
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[" + AR.get_decimal() + "][0-6]: " + tester.get_value(0, 6).toString());

    }

    @Test
    public void testGet_metaCommand() throws Exception {

        System.out.println("~ testGet_metaCommand ~");

        String metaCommand = "Some assembly code!";

        tester.set_metaCommand(metaCommand);
        assertEquals(metaCommand, tester.get_metaCommand(AR.get_decimal()));
        System.out.println("Meta command: " + metaCommand + " | Tester[" + AR.get_decimal() + "]: " + tester.get_metaCommand(AR.get_decimal()));

    }

    @Test
    public void testUpdate() throws Exception {

        System.out.println("~ testDisableWrite ~");

        value.set_content("F0F0");
        tester.set_value(value);
        assertFalse(value.toString().equals(tester.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());
        System.out.println("Updating");
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

    }

    @Test
    public void testGet_decimal() throws Exception {

        System.out.println("~ testGet_decimal ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertEquals(0, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_decimal());
        assertEquals(0, tester.get_decimal(5));
        System.out.println("Bit 5:              ^      | Tester[" + AR.get_decimal() + "][5]: " + tester.get_decimal(5));
        assertEquals(0, tester.get_decimal(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[" + AR.get_decimal() + "][9-14]: " + tester.get_decimal(9, 14));

        value.set_content(255);
        tester.set_value(value);
        tester.update();
        assertEquals(255, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_decimal());
        assertEquals(0, tester.get_decimal(8));
        System.out.println("Bit 8:           ^         | Tester[" + AR.get_decimal() + "][8]: " + tester.get_decimal(8));
        assertEquals(31, tester.get_decimal(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[" + AR.get_decimal() + "][3-9]: " + tester.get_decimal(3, 9));

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertEquals(43690, tester.get_decimal());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_decimal());
        assertEquals(1, tester.get_decimal(13));
        System.out.println("Bit 13:     ^              | Tester[" + AR.get_decimal() + "][13]: " + tester.get_decimal(13));
        assertEquals(42, tester.get_decimal(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[" + AR.get_decimal() + "][0-6]: " + tester.get_decimal(0, 6));

    }

    @Test
    public void testEvaluateAsBoolean() throws Exception {

        System.out.println("~ testEvaluateAsBoolean ~");

        value.set_content(0);
        tester.set_value(value);
        tester.update();
        assertFalse(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.evaluateAsBoolean());
        assertFalse(tester.evaluateAsBoolean(5));
        System.out.println("Bit 5:              ^      | Tester[" + AR.get_decimal() + "][5]: " + tester.evaluateAsBoolean(5));
        assertFalse(tester.evaluateAsBoolean(3, 9));
        System.out.println("Bits 3-9:       ^^^^^^^    | Tester[" + AR.get_decimal() + "][3-9]: " + tester.evaluateAsBoolean(3, 9));

        value.set_content(255);
        tester.set_value(value);
        tester.update();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.evaluateAsBoolean());
        assertFalse(tester.evaluateAsBoolean(8));
        System.out.println("Bit 8:           ^         | Tester[" + AR.get_decimal() + "][8]: " + tester.evaluateAsBoolean(8));
        assertFalse(tester.evaluateAsBoolean(9, 14));
        System.out.println("Bits 9-14: ^^^^^^          | Tester[" + AR.get_decimal() + "][9-14]: " + tester.evaluateAsBoolean(9, 14));

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertTrue(tester.evaluateAsBoolean());
        System.out.println("Value:    " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.evaluateAsBoolean());
        assertTrue(tester.evaluateAsBoolean(13));
        System.out.println("Bit 13:     ^              | Tester[" + AR.get_decimal() + "][13]: " + tester.evaluateAsBoolean(13));
        assertTrue(tester.evaluateAsBoolean(0, 6));
        System.out.println("Bits 0-6:          ^^^^^^^ | Tester[" + AR.get_decimal() + "][0-6]: " + tester.evaluateAsBoolean(0, 6));

    }

    @Test
    public void testEnableWrite() throws Exception {

        System.out.println("~ testEnableWrite ~");

        tester.disableWrite();

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertFalse(value.toString().equals(tester.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

        System.out.println("Enabling Write");
        tester.enableWrite();

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

    }

    @Test
    public void testDisableWrite() throws Exception {

        System.out.println("~ testDisableWrite ~");

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        assertEquals(value.toString(), tester.get_value().toString());
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

        System.out.println("Disabling Write");
        tester.disableWrite();

        value.set_content("F0F0");
        tester.set_value(value);
        tester.update();
        assertFalse(value.toString().equals(tester.get_value().toString()));
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

    }

    @Test
    public void testIsWritable() throws Exception {

        System.out.println("~ testIsWritable ~");

        value.set_content("AAAA");
        tester.set_value(value);
        tester.update();
        if (tester.isWritable()) {
            System.out.println("Write is enabled");
            assertEquals(value.toString(), tester.get_value().toString());
        } else {
            System.out.println("Write is disabled");
            assertFalse(value.toString().equals(tester.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

        System.out.println("Disabling Write");
        tester.disableWrite();

        value.set_content("F0F0");
        tester.set_value(value);
        tester.update();
        if (tester.isWritable()) {
            System.out.println("Write is enabled");
            assertEquals(value.toString(), tester.get_value().toString());
        } else {
            System.out.println("Write is disabled");
            assertFalse(value.toString().equals(tester.get_value().toString()));
        }
        System.out.println("Value: " + value.toString() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_value().toString());

    }

    @Test
    public void testGet_row() throws Exception {

        System.out.println("~ testGet_row ~");

        int size = AR.get_decimal() + 1;
        for (AR.clear(), AR.update(); AR.get_decimal() < size; AR.increment(), AR.update()){
            assertEquals(tester.get_value().toString(), tester.get_row(AR.get_decimal()).toString());
            System.out.println("Value: " + tester.get_value() + " | Tester[" + AR.get_decimal() + "]: " + tester.get_row(AR.get_decimal()));
        }

    }
}