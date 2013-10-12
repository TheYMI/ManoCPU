package Emulator.Components;

import Emulator.Exceptions.NegativeIdException;

import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 00:36
 * Description: This class defines the instruction timer.
 *              The timer defines the cycle number, which defines the micro operations that should be executed.
 * ***********************
 */

public class InstructionTimer extends Component {

    // Member:
    int     mCurrentCycle = 0;
    boolean mReset        = false;

    // Constructor:
    public InstructionTimer(int id, String name){
        try{ // A negative (illegal) ID will throw a NegativeId exception.
            set_id(id);
        } catch (NegativeIdException ni){
            JOptionPane.showMessageDialog(null, ni.get_message());
        }
        set_name(name);
    }

    // Getter:
    public int get_currentCycle(){
        return mCurrentCycle;
    }

    /**************************************************/

    // Advances the timer by one cycle.
    // Parameters:
    // Return value:
    public void pulse(){
        if (mReset){ // If it was reset, don't change the counter (Is at 0) and unset the reset flag for the next cycle.
            mReset = false;
        } else {     // If was not reset, increase the cycle counter.
            mCurrentCycle = (++mCurrentCycle) % TIMER_LIMIT; // When reaches the max value, resets to 0.
        }
    }

    // Resets the timer to 0.
    // Parameters:
    // Return value:
    public void reset(){
        mCurrentCycle = 0; // Goes to 0.
        mReset = true;     // Sets the reset flag.
    }

}
