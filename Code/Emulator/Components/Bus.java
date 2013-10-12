package Emulator.Components;

import Emulator.Exceptions.NegativeIdException;
import Emulator.Global.*;
import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 15:13
 * Description: Bus is a class that emulates a hardware bus that connects two or more Emulator.Components to one another.
 *              Bus is a type of component.
 * ***********************
 */

public class Bus extends Component implements DataComponent {

    // Member:
    Value mValue = new Value(BUS_SIZE);

    // Constructor:
    public Bus(int id, String name){
        try{ // A negative (illegal) ID will throw a NegativeId exception.
            set_id(id);
        } catch (NegativeIdException ni){
            JOptionPane.showMessageDialog(null, ni.get_message());
        }
        set_name(name);
    }

    // Setter:
    public void set_value(Value value){
        mValue.set_content(value);
    }

    // Getter:
    public Value get_value(){
        return mValue;
    }

    // Gets the value in the bus.
    // Parameters: Address (Irrelevant).
    // Return value: The value of the bus.
    public Value get_row(int address){
        return get_value();
    }

    // Gets the value of the bits in the range given.
    // Parameters: Starting Index, Ending Index.
    // Return value: The value of the bits range.
    public Value get_value(int start, int end){

        int size = (end - start) + 1; // The range size + 1 to include the last bit.

        // Negative ranges have no value.
        if ((size < 0) || (start < 0)){
            return new Value(1, 0);
        }
        // A range of 1 is the value of a bit.
        if (size == 1){
            return get_value(start);
        }

        boolean[] rangeValue = new boolean[size];
        boolean[] regValue   = mValue.get_content();

        // Arrays are stored in the opposite direction than registers.
        for (int i = ((mValue.get_size() - 1) - start), j = (size - 1); (i >= 0) && (i >= ((mValue.get_size() - 1) - end)); --i, --j){
            rangeValue[j] = regValue[i];
        }

        return new Value(rangeValue);

    }

    // Gets the value in the bit of the register at the index given.
    // Parameters: Index.
    // Return value: The value of the bit.
    public Value get_value(int index){
        return new Value(new boolean[] {mValue.get_content()[(mValue.get_size() - 1) - index]});
    }

    // Returns the boolean evaluation of the bus. False if 0, 1 otherwise.
    // Parameters:
    // Return value: Boolean evaluation.
    public boolean evaluateAsBoolean(){
        return (get_value().get_decimal() != 0);
    }

    // Evaluates the value in the bit of the register at the index given.
    // Parameters: Index.
    // Return value: The boolean value of the bit.
    public boolean evaluateAsBoolean(int bitIndex){
        return mValue.get_content()[(mValue.get_size() - 1) - bitIndex];
    }
    // Evaluates the value of the bits in the range given.
    // Parameters: Starting Index, Ending Index.
    // Return value: The boolean value of the bits range. False if 0, true if not.
    public boolean evaluateAsBoolean(int bitStart, int bitEnd){
        return (get_value(bitStart, bitEnd).get_decimal() != 0);
    }

    // Returns the decimal evaluation of the register.
    // Parameters:
    // Return value: Decimal value.
    public int get_decimal(){
        return get_value().get_decimal();
    }

    // Evaluates the decimal value in the bit of the register at the index given.
    // Parameters: Index.
    // Return value: The decimal value of the bit.
    public int get_decimal(int bitIndex){
        if (mValue.get_content()[(mValue.get_size() - 1) - bitIndex]){
            return 1;
        } else {
            return 0;
        }
    }

    // Evaluates the decimal value of the bits in the range given.
    // Parameters: Starting Index, Ending Index.
    // Return value: The decimal value of the bits range.
    public int get_decimal(int bitStart, int bitEnd){
        return get_value(bitStart, bitEnd).get_decimal();
    }

    // Forced by interface.
    public DataComponent get_input0(){return null;}
    public DataComponent get_input1(){return null;}
    public DataComponent get_output(){return null;}
    public boolean       isWritable(){return true;}
    public boolean       isWritable0(){return false;}
    public boolean       isWritable1(){return false;}
    public boolean       changed(){return false;}
    public void          set_value(int index, Value value){}
    public void          set_value(int start, int end, Value value){}
    public void          enableWrite(){}
    public void          enableWrite0(){}
    public void          enableWrite1(){}
    public void          disableWrite(){}
    public void          disableWrite0(){}
    public void          disableWrite1(){}
    public void          update(){}
    public void          clear(){}
    public void          increment(){}
    public void          set(){}
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
    public void          complement(){}
    public void          complement0(){}
    public void          complement1(){}
    public void          shiftLeft0(int numOfBits, boolean filler){}
    public void          shiftLeft1(int numOfBits, boolean filler){}
    public void          shiftRight0(int numOfBits, boolean filler){}
    public void          shiftRight1(int numOfBits, boolean filler){}

}