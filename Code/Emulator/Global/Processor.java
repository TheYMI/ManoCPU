package Emulator.Global;

import Emulator.Components.*;

import java.util.HashMap;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 18:43
 * Description: The system class defines the CPU architecture.
 *              The system manages the Components and runs the cycle timer.
 *              This class provides the API to the main function, which implements the instruction set.
 * ***********************
 */

public class Processor implements Constants {

    // String -> Int constant Map:
    public static final HashMap<String, Integer> constantTable = new HashMap<String, Integer>();
    static
    {
        constantTable.put("ALU", ALU);
        constantTable.put("ALU_IN0", ALU_IN0);
        constantTable.put("ALU_IN1", ALU_IN1);
        constantTable.put("ALU_OUT", ALU_OUT);
        constantTable.put("M", M);
        constantTable.put("BUS", BUS);
        constantTable.put("AR", AR);
        constantTable.put("PC", PC);
        constantTable.put("DR", DR);
        constantTable.put("AC", AC);
        constantTable.put("IR", IR);
        constantTable.put("TR", TR);
        constantTable.put("TR0", TR0);
        constantTable.put("TR1", TR1);
        constantTable.put("INPR", INPR);
        constantTable.put("OUTR", OUTR);
        constantTable.put("UNREACHABLE", UNREACHABLE);
        constantTable.put("TARGET_REACHED", TARGET_REACHED);
    }

    public DataComponent[] mComponentsList = new DataComponent[DATA_COMPONENTS];

    // Members:
    InstructionTimer mInstructionTimer = new InstructionTimer(TIMER, "Instruction Timer");
    DataTransferMap  mTransferMap      = new DataTransferMap("appData\\DataTransferMap.csv");
    int              mOpCode;

    // Declare flags:
    Flag e   = new Flag(E, "End Carry");
    Flag r   = new Flag(R, "Interrupt Request");
    Flag s   = new Flag(S, "CPU Start");
    Flag i   = new Flag(I, "Indirect");
    Flag ien = new Flag(IEN, "Interrupt Enable");
    Flag fgi = new Flag(FGI, "Input Flag");
    Flag fgo = new Flag(FGO, "Output Flag");

    // Declare registers:
    AddressRegister ar = new AddressRegister(AR, "Address Register");
    AddressRegister pc = new AddressRegister(PC, "Program Counter");
    DataRegister dr    = new DataRegister(DR, "Data Register");
    DataRegister ac    = new DataRegister(AC, "Accumulator");
    DataRegister ir    = new DataRegister(IR, "Instruction Register");
    DataRegister tr0   = new DataRegister(TR0, "Temporary Register 0");
    DataRegister tr1   = new DataRegister(TR1, "Temporary Register 1");
    IORegister inpr    = new IORegister(INPR, "Input Register");
    IORegister outr    = new IORegister(OUTR, "Output Register");

    // Declare bus:
    Bus bus = new Bus(BUS, "Main Bus");

    // Declare Memory:
    Memory memory = new Memory(M, "Main Memory", ar);

    // Declare ALU
    ALU alu = new ALU(ALU, "Arithmetic-Logic Unit", e);

    // Constructor:
    public Processor(){

        // Initialize componentsList"
        mComponentsList[ALU]     = alu;
        mComponentsList[ALU_IN0] = alu.get_input0();
        mComponentsList[ALU_IN1] = alu.get_input1();
        mComponentsList[ALU_OUT] = alu.get_output();
        mComponentsList[M]       = memory;
        mComponentsList[BUS]     = bus;
        mComponentsList[AR]      = ar;
        mComponentsList[PC]      = pc;
        mComponentsList[DR]      = dr;
        mComponentsList[AC]      = ac;
        mComponentsList[IR]      = ir;
        mComponentsList[TR0]     = tr0;
        mComponentsList[TR1]     = tr1;
        mComponentsList[INPR]    = inpr;
        mComponentsList[OUTR]    = outr;
        mComponentsList[E]       = e;
        mComponentsList[R]       = r;
        mComponentsList[S]       = s;
        mComponentsList[I]       = i;
        mComponentsList[IEN]     = ien;
        mComponentsList[FGI]     = fgi;
        mComponentsList[FGO]     = fgo;

        s.set();    // Set to running mode
        s.update();

    }

    // Setter:
    public void set_opCode(int opCode){
        mOpCode = opCode;
    }

    // Getter:
    public int get_opCode(){
        return mOpCode;
    }

    public int get_cycleNum(){
        return mInstructionTimer.get_currentCycle();
    }

    /**************************************************/

    // Loads a given program to the memory.
    // Parameters: Program
    // Return value:
    public void loadProgram(Program program) throws NullPointerException{

        if (program == null){
            throw new NullPointerException("ERROR: No program was loaded!");
        }

        ProgramLine[] lines = program.getLines();

        memory.enableWrite();
        ar.enableWrite();

        for (int i = 0; i < lines.length; ++i){
            ar.set_value(lines[i].get_address());
            ar.update();
            memory.set_value(lines[i].get_content());
            memory.set_metaCommand(lines[i].get_metaCommand());
        }
        memory.update();

        memory.disableWrite();
        ar.disableWrite();

    }

    // Resets the timer.
    // Parameters: Program
    // Return value:
    public void resetTimer(){
        mInstructionTimer.reset();
    }

    // Stops the execution.
    // Parameters:
    // Return value:
    public void halt(){
        s.clear();
    }

    // Checks if the opCode is equal to a given opCode.
    // Parameters: opCode to check.
    // Return value: True if it's the current opcode and false if not.
    public boolean checkOpCode(int opCode){
        return (opCode == mOpCode);
    }

    // Moves the timer to the next cycle and updates all the Components.
    // Parameters:
    // Return value:
    public void nextCycle(){
        for (int i = 0; i < DATA_COMPONENTS; ++i){
            mComponentsList[i].update();
            mComponentsList[i].disableWrite();
        }
        mInstructionTimer.pulse();
    }

    // Moves the data from one data component to another.
    // Parameters: Starting location ID, Target location ID.
    // Return value:
    public void moveData(int fromID, int toID){

        // If the Components are not in the data transfer map, do nothing.
        if ((fromID < 0) || (fromID > DATA_TABLE_SIZE)){
            return;
        }
        if ((toID < 0) || (toID > DATA_TABLE_SIZE)){
            return;
        }

        int nextID    = mTransferMap.nextInRoute(fromID, toID);
        int currentID = fromID;

        while (nextID > 0){ // Transfer as long as the next ID is a component. Negative ID is an error or the destination was reached.

            // Transfer data to next component.
            // No update, so moving is possible only through data streams.
            mComponentsList[nextID].enableWrite();
            mComponentsList[nextID].set_value(mComponentsList[currentID].get_value());
            mComponentsList[nextID].disableWrite();

            // The data was moved to the next component.
            currentID = nextID;
            // Get next component.
            nextID = mTransferMap.nextInRoute(currentID, toID);

        }

    }

    // Moves the data of a value to another.
    // Parameters: Starting location ID, Target location ID.
    // Return value:
    public void moveData(Value value, int toID){
        // Transfer data to next component.
        // No update, so moving is possible only through data streams.
        mComponentsList[toID].enableWrite();
        mComponentsList[toID].set_value(value);
        mComponentsList[toID].disableWrite();

    }

}
