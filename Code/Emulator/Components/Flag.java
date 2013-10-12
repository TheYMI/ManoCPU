package Emulator.Components;

import Emulator.Exceptions.NegativeIdException;
import Emulator.Global.Value;

import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 23:21
 * Description: This class represents a 1-bit flag. A flag is a component.
 *              A flag has a boolean value and a change of state flag that indicates that the flag was set since the last time its value was checked.
 *              The boolean value has a buffer to mimic the master-slave functionality of real flip-flops.
 *              The change of state flag is set to true when the flag is set to true, and resets when the flag is read.
 *              Values are set to the buffer and read from the value. An update method will move the value from the buffer to the value.
 * ***********************
 */

public class Flag extends Component implements DataComponent {

    // Members:
    boolean mValue   = false;
    boolean mBuffer  = false;
    boolean mChanged = false;

    // Constructor:
    public Flag(int id, String name){
        try{ // A negative (illegal) ID will throw a NegativeId exception.
            set_id(id);
        } catch (NegativeIdException ni){
            JOptionPane.showMessageDialog(null, ni.get_message());
        }
        set_name(name);
    }

    // Setter:
    public void set_value(Value value){
        mBuffer = value.get_content()[0]; // Set the flag according to the MSb
        mChanged = mBuffer;               // Status change is true only if the new value is true.
    }

    // Getter:
    public Value get_value(){
        mChanged = false; // Clears the changed state flag once the value is read.

        return new Value(new boolean[] {mValue});
    }

    // Forced by interface.
    public DataComponent get_input0(){return null;}
    public DataComponent get_input1(){return null;}
    public DataComponent get_output(){return null;}
    public boolean       evaluateAsBoolean(int bitIndex){return evaluateAsBoolean();}
    public boolean       evaluateAsBoolean(int bitStart, int bitEnd){return evaluateAsBoolean();}
    public boolean       isWritable(){return true;}
    public boolean       isWritable0(){return false;}
    public boolean       isWritable1(){return false;}
    public Value         get_value(int start, int end){return get_value();}
    public Value         get_value(int index){return get_value();}
    public Value         get_row(int address){return get_value();}
    public void          set_value(int index, Value value){}
    public void          set_value(int start, int end, Value value){}
    public void          enableWrite(){}
    public void          enableWrite0(){}
    public void          enableWrite1(){}
    public void          disableWrite(){}
    public void          disableWrite0(){}
    public void          disableWrite1(){}
    public void          increment(){}
    public void          passThrough0(){}
    public void          passThrough1(){}
    public void          sum(){}
    public void          subtract(){}
    public void          multiply(){}
    public void          divide(){}
    public void          modulo(){}
    public void          and(){}
    public void          or(){}
    public void          xor(){}
    public void          equal(){}
    public void          notEqual(){}
    public void          greaterThan(){}
    public void          lessThan(){}
    public void          greaterOrEqual(){}
    public void          lessOrEqual(){}
    public void          complement0(){}
    public void          complement1(){}
    public void          shiftLeft0(int numOfBits, boolean filler){}
    public void          shiftLeft1(int numOfBits, boolean filler){}
    public void          shiftRight0(int numOfBits, boolean filler){}
    public void          shiftRight1(int numOfBits, boolean filler){}
    public int           get_decimal(int bitIndex){return get_decimal();}
    public int           get_decimal(int bitStart, int bitEnd){return get_decimal();}

    /**************************************************/

    // Returns the boolean evaluation of the register. False if 0, 1 otherwise.
    // Parameters:
    // Return value: Boolean evaluation.
    public boolean evaluateAsBoolean(){
        return (get_value().get_decimal() != 0);
    }

    // Returns the decimal evaluation of the flag.
    // Parameters:
    // Return value: Decimal value.
    public int get_decimal(){
        if (mValue){
            return 1;
        } else {
            return 0;
        }
    }

    // Sets the flag value to true. This sets the changed state flag to true.
    // Parameters:
    // Return value:
    public void set(){
        mBuffer = true;
        mChanged = true;
    }

    // Sets the flag value to false. This sets the changed state flag to true.
    // Parameters:
    // Return value:
    public void clear(){
        mBuffer = false;
        mChanged = false;
    }

    // Reverses the flag value. If the value becomes true, it sets the changed state flag to true.
    // Parameters:
    // Return value:
    public void complement(){
        mBuffer = !mBuffer;
        mChanged = mBuffer;
    }

    // Returns true if the flag was set since the last read and false if it didn't.
    // Parameters:
    // Return value: True if the flag was set since the last read and false if it didn't.
    public boolean changed(){
        return mChanged;
    }

    // Moves the value of the buffer to the value.
    // Parameters:
    // Return value:
    public void update(){
        mValue = mBuffer;
    }

}
