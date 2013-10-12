package Emulator.Global;

import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 15:26
 * Description: This class will contain constants used by the system.
 * ***********************
 */

public interface Constants {

    // Set bit values to 0 and 1.
    public static final boolean _0 = false;
    public static final boolean _1 = true;

    // Set timeout value.
    public static final int TIMEOUT = 10000;

    // Components' bit sizes:
    public static final int DATA_REGISTER_SIZE  = 16;
    public static final int ADDR_REGISTER_SIZE  = 12;
    public static final int IO_REGISTER_SIZE    = 8;
    public static final int MEMORY_SIZE         = (int)Math.pow(2, ADDR_REGISTER_SIZE); // Memory size depends on the max address value.
    public static final int BUS_SIZE            = DATA_REGISTER_SIZE;
    public static final int DATA_COMPONENTS     = 22;
    public static final int DATA_TABLE_SIZE     = 15;

    // Instruction timer limit:
    public static final int TIMER_LIMIT = 16;

    // Data Components IDs:
    public static final int ALU     = 0;
    public static final int ALU_IN0 = 1;
    public static final int ALU_IN1 = 2;
    public static final int ALU_OUT = 3;
    public static final int M       = 4;
    public static final int BUS     = 5;
    public static final int AR      = 6;
    public static final int PC      = 7;
    public static final int DR      = 8;
    public static final int AC      = 9;
    public static final int IR      = 10;
    public static final int TR      = 11;
    public static final int TR0     = TR;
    public static final int TR1     = 12;
    public static final int INPR    = 13;
    public static final int OUTR    = 14;
    public static final int E       = 15;
    public static final int R       = 16;
    public static final int S       = 17;
    public static final int I       = 18;
    public static final int IEN     = 19;
    public static final int FGI     = 20;
    public static final int FGO     = 21;

    // Non-Data Components IDs:
    public static final int TIMER = 22;

    // Map special actions:
    public static final int UNREACHABLE    = -99;
    public static final int TARGET_REACHED = -1;

}
