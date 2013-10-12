package Emulator.Global;

import Assembler.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 18:32
 * Description: The emulator contains the main function.
 *              The main function runs the emulation on the system.
 *              The main function will create the GUI and wil function according to the user's commands.
 * ***********************
 */

public class Emulator implements Constants {

    // Creates a system and binds it with an instruction set.
    Processor          mManoCPU = new Processor();
    iInstructionsUCode mUCode;
    iAssembler         mAssembler;
    String             mCycleDescription;
    String             mAssemblyCommand;

    public boolean run(boolean uCode){
        mAssemblyCommand = mManoCPU.memory.get_metaCommand(mManoCPU.pc.get_decimal()); // Get assembly command.
        while (mManoCPU.mComponentsList[S].evaluateAsBoolean()){
            switch (mManoCPU.get_cycleNum()){
                case 0:  mCycleDescription = mUCode.t0();  break;
                case 1:  mCycleDescription = mUCode.t1();  break;
                case 2:  mCycleDescription = mUCode.t2();  break;
                case 3:  mCycleDescription = mUCode.t3();  break;
                case 4:  mCycleDescription = mUCode.t4();  break;
                case 5:  mCycleDescription = mUCode.t5();  break;
                case 6:  mCycleDescription = mUCode.t6();  break;
                case 7:  mCycleDescription = mUCode.t7();  break;
                case 8:  mCycleDescription = mUCode.t8();  break;
                case 9:  mCycleDescription = mUCode.t9();  break;
                case 10: mCycleDescription = mUCode.t10(); break;
                case 11: mCycleDescription = mUCode.t11(); break;
                case 12: mCycleDescription = mUCode.t12(); break;
                case 13: mCycleDescription = mUCode.t13(); break;
                case 14: mCycleDescription = mUCode.t14(); break;
                case 15: mCycleDescription = mUCode.t15(); break;
            }

            if (uCode || (mManoCPU.get_cycleNum() == 0)){
                return true; // Still running.
            }
        }

        return false; // Run ended.
    }

    public void assemble(){
        mAssembler.assemble();
    }

    public String getSystemComponent(int componentID){
        return mManoCPU.mComponentsList[componentID].get_value().get_hexadecimal();
    }

    public String getCycleDescription(){
        return mCycleDescription;
    }

    public String getAssemblyCommand(){
        return mAssemblyCommand;
    }

    public String[][] getSystemMemory(){
        String[][] data = new String[MEMORY_SIZE][2];

        for (int i = 0; i < MEMORY_SIZE; ++i) {
            data[i][0] = Integer.toHexString(i).toUpperCase();
            data[i][1] = mManoCPU.memory.get_row(i).get_hexadecimal();
        }

        return data;
    }

    // Returns the system to pre-run values.
    // Parameters:
    // Return value:
    public void reset(){

        // Reset all the components:
        for (int i = 0; i < DATA_COMPONENTS; ++i){
            mManoCPU.mComponentsList[i].enableWrite();
            mManoCPU.mComponentsList[i].clear();
            mManoCPU.mComponentsList[i].disableWrite();
        }

        // Reset the start flag:
        mManoCPU.s.enableWrite();
        mManoCPU.s.set();
        mManoCPU.s.disableWrite();
        mManoCPU.s.update();

        // Reset the instructions' timer:
        mManoCPU.resetTimer();
        mManoCPU.nextCycle();

        // Reload the program to the memory:
        getProgram();
    }

    // Stops the currently running program.
    // Parameters:
    // Return value:
    public void stopProgram(){
        mManoCPU.s.enableWrite();
        mManoCPU.s.clear();
        mManoCPU.s.disableWrite();
        mManoCPU.s.update();
    }

    // Notifies that there's a new input by setting the FGI flag.
    // Parameters:
    // Return value:
    public void notifyInput(){
        mManoCPU.fgi.enableWrite();
        mManoCPU.fgi.set();
        mManoCPU.fgi.disableWrite();
        mManoCPU.fgi.update();
    }

    // Notifies that the output device is ready by setting the FGI flag.
    // Parameters:
    // Return value:
    public void notifyOutput(){
        mManoCPU.fgo.enableWrite();
        mManoCPU.fgo.set();
        mManoCPU.fgo.disableWrite();
        mManoCPU.fgo.update();
    }

    // Read the program from a file.
    public void getProgram(){

        Program program  = new Program();
        String  filename = "appData\\Program.csv";

        try {

            FileReader     inputFile  = new FileReader(filename);
            BufferedReader buffer     = new BufferedReader(inputFile);
            String         line, metaCommand;
            Value          address, content;

            address = new Value(ADDR_REGISTER_SIZE);
            content = new Value(DATA_REGISTER_SIZE);

            while ((line = buffer.readLine()) != null){
                String[] values = line.split(",");

                address.set_content(values[0]);
                content.set_content(values[1]);
                metaCommand = values[2];

                program.addLine(new ProgramLine(address, content, metaCommand));
            }

        } catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null, "ERROR: Could not open " + filename + " for reading!");
            program = null;
        } catch (IOException ioe){
            JOptionPane.showMessageDialog(null, "ERROR: Could not read from " + filename + "!");
            program = null;
        }

        mManoCPU.loadProgram(program);

    }

    // Dynamically load an instruction set.
    public void loadTemplate(String templateName) throws Exception, Error{
        try {
            Loader loader = new Loader(templateName);
            mAssembler = (iAssembler)loader.loadClass("Assembler." + templateName + "Assembler").newInstance();
            mUCode     = (iInstructionsUCode)loader.loadClass("Emulator.Global." + templateName + "UCode").newInstance();
            mUCode.setCPU(mManoCPU);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: Can't load " + e.getMessage() + "!");
            throw e;
        } catch (Error e){
            JOptionPane.showMessageDialog(null, "ERROR: Can't load " + e.getMessage() + "!");
            throw e;
        }
    }

    // Load the default instruction set.
    public void loadDefaultTemplate(){
        mAssembler = new Assembler();
        mUCode     = new InstructionsUCode();
        mUCode.setCPU(mManoCPU);
    }

}
