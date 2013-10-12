package Tests.Emulator.Global;

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
 * Time: 21:51
 * Description:
 * ***********************
 */

public class ValueTest {


    Value tester;

    @Before
    public void setUp() {
        tester = new Value(Constants.DATA_REGISTER_SIZE);
    }

    @After
    public void tearDown() {
        System.out.println("");
        tester = null;
    }

    @Test
    public void testSet_content() throws Exception {

        tester.set_content(93);
        assertEquals(93, tester.get_decimal());

        tester.set_content(new boolean[]{true, true, true});
        assertEquals(7, tester.get_decimal());

        tester.set_content("A");
        assertEquals(10, tester.get_decimal());

        tester.set_content(new Value("F"));
        assertEquals(15, tester.get_decimal());

    }

    @Test
    public void testGet_size() throws Exception {

        assertEquals(Constants.DATA_REGISTER_SIZE, tester.get_size());

    }

    @Test
    public void testGet_content() throws Exception {

        assertNotNull(tester.get_content());

    }

    @Test
    public void testGet_decimal() throws Exception {

        tester.set_content("F");
        assertEquals(15, tester.get_decimal());

    }

    @Test
    public void testGet_hexadecimal() throws Exception {

        tester.set_content(15);
        assertEquals("000F", tester.get_hexadecimal());

    }

    @Test
    public void testToBinary() throws Exception {

        boolean value[]    = Value.toBinary(4, 10);
        boolean expected[] = {true, false, true, false};
        for (int i = 0; i < 4; ++i) {
            assertEquals(expected[i], value[i]);
        }

        value = Value.toBinary("A");
        for (int i = 0; i < 4; ++i) {
            assertEquals(expected[i], value[i]);
        }

    }

    @Test
    public void testToDecimal() throws Exception {

        assertEquals(7, Value.toDecimal(new boolean[]{true, true, true}));
        assertEquals(15, Value.toDecimal("F"));

    }

    @Test
    public void testToHexadecimal() throws Exception {

        assertEquals("7", Value.toHexadecimal(new boolean[]{true, true, true}));
        assertEquals("9", Value.toHexadecimal("1001"));
        assertEquals("03", Value.toHexadecimal(2, 3));

    }

    @Test
    public void testToString() throws Exception {

        tester.set_content(12);
        assertEquals("0000000000001100", tester.toString());

    }

    @Test
    public void testComplement() throws Exception {

        tester.set_content("F0F0");
        tester.complement();
        assertEquals("0F0F", tester.get_hexadecimal());

    }

    @Test
    public void testIncrement() throws Exception {

        tester.set_content(36);
        tester.increment();
        assertEquals(37, tester.get_decimal());

    }
}
