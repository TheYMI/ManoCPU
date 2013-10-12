package Emulator.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 14:36
 * Description: The Program class defines a whole program that needs to be loaded to the memory.
 * ***********************
 */

public class Program {

    // Members:
    List<ProgramLine> mLines = new ArrayList<ProgramLine>();

    /**************************************************/

    // Adds a line to the program.
    // Parameters: Program line to be added.
    // Return value:
    public void addLine(ProgramLine programLine){
        mLines.add(programLine);
    }

    // Returns an array containing the program's lines.
    // Parameters:
    // Return value: All the lines as an array.
    public ProgramLine[] getLines(){
        return mLines.toArray(new ProgramLine[mLines.size()]);
    }

    // Removes all the lines from the program.
    // Parameters:
    // Return value:
    public void clear(){
        mLines.clear();
    }

}
