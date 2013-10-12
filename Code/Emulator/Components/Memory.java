package Emulator.Components;

import Emulator.Exceptions.NegativeIdException;
import Emulator.Global.Value;
import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 23:57
 * Description: The memory class defines the system's memory.
 *              The memory is composed of binary values, and its size is defined by the max value of the address register (MEMORY_SIZE).
 *              In addition to the values, the memory points to an address register that serves as the index to the memory.
 *              There's also a write enable flag. Values can be set only if the write enable flag is true.
 *              A buffer memory exists to mimic the the master-slave functionality of real flip-flops.
 *              Values are written to the buffer and read from the memory.
 *              An update method will update all the memory's registers.
 * ***********************
 */

public class Memory extends Component implements DataComponent {

    // Members:
    Value[]         mMemory      = new Value[MEMORY_SIZE];
    Value[]         mBuffer      = new Value[MEMORY_SIZE];
    String[]        mMetaCommand = new String[MEMORY_SIZE];
    AddressRegister mAddress;
    boolean         mWriteEnable = false;

    // Constructor:
    public Memory(int id, String name, AddressRegister addressRegister){
        mAddress = addressRegister;
        try{ // A negative (illegal) ID will throw a NegativeId exception.
            set_id(id);
        } catch (NegativeIdException ni){
            JOptionPane.showMessageDialog(null, ni.get_message());
        }
        set_name(name);

        for (int i = 0; i < MEMORY_SIZE; ++i){
            mMemory[i] = new Value(DATA_REGISTER_SIZE);
            mBuffer[i] = new Value(DATA_REGISTER_SIZE);
        }
    }

    // Setter:
    public void set_value(Value value){
        if (mWriteEnable){   // Write only if writing is enabled.
            mBuffer[Value.toDecimal(mAddress.get_value().get_content())].set_content(value); // Write to the buffer to mimic master-slave functionality.
        }
    }

    public void set_metaCommand(String metaCommand){
        mMetaCommand[Value.toDecimal(mAddress.get_value().get_content())] = metaCommand;
    }

    // Getter:
    public Value get_value(){
        return mMemory[Value.toDecimal(mAddress.get_value().get_content())]; // Read the current value.
    }

    public String get_metaCommand(int address){
        return mMetaCommand[address];
    }

    // Forced by interface.
    public DataComponent get_input0(){return null;}
    public DataComponent get_input1(){return null;}
    public DataComponent get_output(){return null;}
    public boolean       isWritable0(){return false;}
    public boolean       isWritable1(){return false;}
    public boolean       changed(){return false;}
    public void          set_value(int index, Value value){}
    public void          set_value(int start, int end, Value value){}
    public void          enableWrite0(){}
    public void          enableWrite1(){}
    public void          disableWrite0(){}
    public void          disableWrite1(){}
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

    /**************************************************/

    // Returns the value of the row in the specified address.
    // Parameters: Address
    // Return value: Value of the row.
    public Value get_row(int address){
        return new Value(mMemory[address].get_content());
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
        boolean[] regValue   = mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_content();

        // Arrays are stored in the opposite direction than registers.
        for (int i = ((mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_size() - 1) - start), j = (size - 1); (i >= 0) && (i >= ((mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_size() - 1) - end)); --i, --j){
            rangeValue[j] = regValue[i];
        }

        return new Value(rangeValue);

    }

    // Gets the value in the bit of the register at the index given.
    // Parameters: Index.
    // Return value: The value of the bit.
    public Value get_value(int index){
        return new Value(new boolean[] {mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_content()[(mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_size() - 1) - index]});
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
        if (mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_content()[(mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_size() - 1) - bitIndex]){
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

    // Returns the boolean evaluation of the memory location pointed by the address register. False if 0, 1 otherwise.
    // Parameters:
    // Return value: Boolean evaluation.
    public boolean evaluateAsBoolean(){
        return (get_value().get_decimal() != 0);
    }


    // Evaluates the value in the bit of the register at the index given.
    // Parameters: Index.
    // Return value: The boolean value of the bit.
    public boolean evaluateAsBoolean(int bitIndex){
        return mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_content()[(mMemory[Value.toDecimal(mAddress.get_value().get_content())].get_size() - 1) - bitIndex];
    }

    // Evaluates the value of the bits in the range given.
    // Parameters: Starting Index, Ending Index.
    // Return value: The boolean value of the bits range. False if 0, true if not.
    public boolean evaluateAsBoolean(int bitStart, int bitEnd){
        return (get_value(bitStart, bitEnd).get_decimal() != 0);
    }

    // Enables the writing to the memory.
    // Parameters:
    // Return value:
    public void enableWrite(){
        mWriteEnable = true;
    }

    // Disables the writing to the memory.
    // Parameters:
    // Return value:
    public void disableWrite(){
        mWriteEnable = false;
    }

    // Checks if writing to the memory is enabled.
    // Parameters:
    // Return value: True if enabled, false if not.
    public boolean isWritable(){
        return mWriteEnable;
    }

    // Updated the value of all the memory slots to the value of the corresponding buffer.
    // Parameters:
    // Return value:
    public void update(){
        for (int i = 0; i < MEMORY_SIZE; ++i){
            mMemory[i].set_content(mBuffer[i].get_content());
        }
    }

}
