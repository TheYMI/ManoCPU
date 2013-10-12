package Emulator.Global;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 15:04
 * Description: The Data Transfer Map acts as a routing table for data.
 *              Each row defines the current component holding the data.
 *              Each column defines the target component to which the data is transferred.
 *              A cell defined by a row and column defines the next component to receive the data.
 *              Special values are designated to special actions:
 *              UNREACHABLE    - The data can't reach the target from its current location.
 *              TARGET_REACHED - Current location is the target.
 * ***********************
 */

public class DataTransferMap implements Constants {

    // Member:
    int[][] mMap;

    // Constructor:
    public DataTransferMap(String filename){

        mMap = new int[DATA_TABLE_SIZE][DATA_TABLE_SIZE];

        try {

            FileReader     inputFile = new FileReader(filename);
            BufferedReader buffer    = new BufferedReader(inputFile);
            String         line;

            for (int i = 0; i < DATA_TABLE_SIZE; ++i){
                for (int j = 0; j < DATA_TABLE_SIZE; ++j){
                    if (i == j){
                        mMap[i][j] = TARGET_REACHED;
                    } else {
                        mMap[i][j] = UNREACHABLE;
                    }
                }
            }

            while ((line = buffer.readLine()) != null){
                int      current, target, next;
                String[] values = line.split(",");

                current = Processor.constantTable.get(values[0]);
                target  = Processor.constantTable.get(values[1]);
                next    = Processor.constantTable.get(values[2]);

                if ((current == UNREACHABLE) || (target == UNREACHABLE)){
                    throw new Error("Cant move to or from UNREACHABLE");
                }
                if ((current == TARGET_REACHED) || (target == TARGET_REACHED)){
                    throw new Error("Cant move to or from TARGET_REACHED");
                }

                mMap[current][target] = next;
            }

        } catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null, "ERROR: Could not open " + filename + " for reading!");
        } catch (IOException ioe){
            JOptionPane.showMessageDialog(null, "ERROR: Could not read from " + filename + "!");
        }

    }

    /**************************************************/

    // Returns the next component ID according to the current and target Components.
    // Parameters: Current and Target Components' IDs
    // Return value: Next component ID or special value.
    public int nextInRoute(int currID, int targetID){
        return mMap[currID][targetID];
    }

}
