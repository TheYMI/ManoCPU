package Emulator.Components;

import Emulator.Exceptions.NegativeIdException;
import Emulator.Global.Value;
import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 00:51
 * Description: The ALU is the class that executes all the logical and arithmetical operations.
 *              The ALU has two binary inputs for up to two operands, and a binary output for the solution.
 *              Each of the inputs and outputs has its own ID, as opposed to one ID for the ALU.
 *              Both inputs and the output have the same size as a data register.
 *              Each input has its own write enable flag. Inputs can be set only if writing is enabled.
 *              The ALU will also point to an end carry flag.
 * ***********************
 */

public class ALU extends Component implements DataComponent {

    // Inner classes:
    class Input extends Component implements DataComponent {
        // Members:
        Value   mValue       = new Value(DATA_REGISTER_SIZE);
        boolean mWriteEnable = false;

        // Constructor:
        public Input(int id, String name){
            try{ // A negative (illegal) ID will throw a NegativeId exception.
                set_id(id);
            } catch (NegativeIdException ni){
                JOptionPane.showMessageDialog(null, ni.get_message());
            }
            set_name(name);
        }

        // Setter:
        public void set_value(Value value){
            if (mWriteEnable){
                mValue.set_content(value);
            }
        }

        // Getter:
        public Value get_value(){
            return mValue;
        }

        // Forced by interface.
        public DataComponent get_input0(){return null;}
        public DataComponent get_input1(){return null;}
        public DataComponent get_output(){return null;}
        public boolean       isWritable0(){return false;}
        public boolean       isWritable1(){return false;}
        public boolean       changed(){return false;}
        public Value         get_row(int address){return get_value();}
        public void          set_value(int index, Value value){}
        public void          set_value(int start, int end, Value value){}
        public void          enableWrite0(){}
        public void          enableWrite1(){}
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

        /**************************************************/

        // Returns the boolean evaluation of the input. False if 0, 1 otherwise.
        // Parameters:
        // Return value: Boolean evaluation.
        public boolean evaluateAsBoolean(){
            return (get_value().get_decimal() != 0);
        }

        // Evaluates the value in the bit of the input at the index given.
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

        // Gets the value in the bit of the input at the index given.
        // Parameters: Index.
        // Return value: The value of the bit.
        public Value get_value(int index){
            return new Value(new boolean[] {mValue.get_content()[(mValue.get_size() - 1) - index]});
        }

        // Returns the decimal evaluation of the input.
        // Parameters:
        // Return value: Decimal value.
        public int get_decimal(){
            return get_value().get_decimal();
        }

        // Evaluates the decimal value in the bit of the input at the index given.
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

        // Enables the writing to the input.
        // Parameters:
        // Return value:
        public void enableWrite(){
            mWriteEnable = true;
        }

        // Disables the writing to the input.
        // Parameters:
        // Return value:
        public void disableWrite(){
            mWriteEnable = false;
        }

        // Checks if writing to the input is enabled.
        // Parameters:
        // Return value: True if enabled, false if not.
        public boolean isWritable(){
            return mWriteEnable;
        }

    }

    class Output extends Component implements DataComponent {
        // Members:
        Value mValue = new Value(DATA_REGISTER_SIZE);

        // Constructor:
        public Output(int id, String name){
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

        // Returns the boolean evaluation of the output. False if 0, 1 otherwise.
        // Parameters:
        // Return value: Boolean evaluation.
        public boolean evaluateAsBoolean(){
            return (get_value().get_decimal() != 0);
        }

        // Forced by interface.
        public DataComponent get_input0(){return null;}
        public DataComponent get_input1(){return null;}
        public DataComponent get_output(){return null;}
        public boolean       isWritable(){return true;}
        public boolean       isWritable0(){return true;}
        public boolean       isWritable1(){return true;}
        public boolean       changed(){return false;}
        public Value         get_row(int address){return get_value();}
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

        /**************************************************/

        // Evaluates the value in the bit of the output at the index given.
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

        // Gets the value in the bit of the output at the index given.
        // Parameters: Index.
        // Return value: The value of the bit.
        public Value get_value(int index){
            return new Value(new boolean[] {mValue.get_content()[(mValue.get_size() - 1) - index]});
        }

        // Returns the decimal evaluation of the input.
        // Parameters:
        // Return value: Decimal value.
        public int get_decimal(){
            return get_value().get_decimal();
        }

        // Evaluates the decimal value in the bit of the output at the index given.
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

    }

    /**************************************************/

    // Members:
    public Input  mInput0;
    public Input  mInput1;
    public Output mOutput;
    Flag          mEndCarry;

    // Constructor:
    public ALU(int id, String name, Flag endCarry){
        mInput0 = new Input(ALU_IN0, "ALU " + id + " Input 0");
        mInput1 = new Input(ALU_IN1, "ALU " + id + " Input 1");
        mOutput = new Output(ALU_OUT, "ALU " + id + " Output");
        mEndCarry = endCarry;
        try{ // A negative (illegal) ID will throw a NegativeId exception.
            set_id(id);
        } catch (NegativeIdException ni){
            JOptionPane.showMessageDialog(null, ni.get_message());
        }
        set_name(name);
    }

    // Getters:
    public Value get_value(){
        return mOutput.get_value();
    }

    public DataComponent get_input0(){
        return mInput0;
    }

    public DataComponent get_input1(){
        return mInput1;
    }

    public DataComponent get_output(){
        return mOutput;
    }

    public boolean evaluateAsBoolean(){
        return mOutput.evaluateAsBoolean();
    }

    // Forced by interface.
    public boolean isWritable(){return false;}
    public boolean changed(){return false;}
    public Value   get_row(int address){return get_value();}
    public void    disableWrite(){}
    public void    set_value(Value value){}
    public void    set_value(int index, Value value){}
    public void    set_value(int start, int end, Value value){}
    public void    enableWrite(){}
    public void    update(){}
    public void    clear(){}
    public void    increment(){}
    public void    set(){}
    public void    complement(){}

    /**************************************************/

    // Evaluates the value in the bit of the output at the index given.
    // Parameters: Index.
    // Return value: The boolean value of the bit.
    public boolean evaluateAsBoolean(int bitIndex){
        return mOutput.get_value().get_content()[(mOutput.get_value().get_size() - 1) - bitIndex];
    }

    // Evaluates the value of the bits in the range given.
    // Parameters: Starting Index, Ending Index.
    // Return value: The boolean value of the bits range. False if 0, true if not.
    public boolean evaluateAsBoolean(int bitStart, int bitEnd){
        return (get_value(bitStart, bitEnd).get_decimal() != 0);
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
        boolean[] regValue   = mOutput.get_value().get_content();

        // Arrays are stored in the opposite direction than registers.
        for (int i = ((mOutput.get_value().get_size() - 1) - start), j = (size - 1); (i >= 0) && (i >= ((mOutput.get_value().get_size() - 1) - end)); --i, --j){
            rangeValue[j] = regValue[i];
        }

        return new Value(rangeValue);

    }

    // Gets the value in the bit of the output at the index given.
    // Parameters: Index.
    // Return value: The value of the bit.
    public Value get_value(int index){
        return new Value(new boolean[] {mOutput.get_value().get_content()[(mOutput.get_value().get_size() - 1) - index]});
    }

    // Returns the decimal evaluation of the input.
    // Parameters:
    // Return value: Decimal value.
    public int get_decimal(){
        return get_value().get_decimal();
    }

    // Evaluates the decimal value in the bit of the output at the index given.
    // Parameters: Index.
    // Return value: The decimal value of the bit.
    public int get_decimal(int bitIndex){
        if (mOutput.get_value().get_content()[(mOutput.get_value().get_size() - 1) - bitIndex]){
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

    // Enables the writing to input 0.
    // Parameters:
    // Return value:
    public void enableWrite0(){
        mInput0.enableWrite();
    }

    // Enables the writing to input 1.
    // Parameters:
    // Return value:
    public void enableWrite1(){
        mInput1.enableWrite();
    }

    // Disables the writing to input 0.
    // Parameters:
    // Return value:
    public void disableWrite0(){
        mInput0.disableWrite();
    }

    // Disables the writing to input 1.
    // Parameters:
    // Return value:
    public void disableWrite1(){
        mInput1.disableWrite();
    }

    // Checks if writing to input 0 is enabled.
    // Parameters:
    // Return value: True if enabled, false if not.
    public boolean isWritable0(){
        return mInput0.isWritable();
    }

    // Checks if writing to input 1 is enabled.
    // Parameters:
    // Return value: True if enabled, false if not.
    public boolean isWritable1(){
        return mInput1.isWritable();
    }

    // Moves the value of input 0 to the output.
    // Parameters:
    // Return value:
    public void passThrough0(){
        mOutput.set_value(mInput0.get_value());
    }

    // Moves the value of input 1 to the output.
    // Parameters:
    // Return value:
    public void passThrough1(){
        mOutput.set_value(mInput1.get_value());
    }

    /**************ARITHMETIC**OPERATIONS**************/

    // Sums the values of both inputs and places the result in the output. If there is an end carry, it sets the end carry flag.
    // Parameters:
    // Return value:
    public void sum(){

        int       size = DATA_REGISTER_SIZE + 1; // Size of data + end carry.
        int       resultInt;
        boolean[] resultBool;

        resultInt  = Value.toDecimal(mInput0.get_value().get_content()) + Value.toDecimal(mInput1.get_value().get_content());
        resultBool = Value.toBinary(size, resultInt);

        mOutput.set_value(new Value(resultBool));
        if (resultBool[0]){
            mEndCarry.set();
        }

    }

    // Subtracts the value of input 1 from input 0 and places the result in the output.
    // Parameters:
    // Return value:
    public void subtract(){

        int       resultInt;
        boolean[] resultBool;

        Value val = new Value(mInput1.get_value().get_content());

        val.complement();
        val.increment();
        mInput1.set_value(val);

        sum();

    }

    // Multiplies the value of both inputs and places the result in the output. Data will be lost if the result is too big.
    // Parameters:
    // Return value:
    public void multiply(){

        int       resultInt;
        boolean[] resultBool;

        resultInt  = Value.toDecimal(mInput0.get_value().get_content()) * Value.toDecimal(mInput1.get_value().get_content());
        resultBool = Value.toBinary(DATA_REGISTER_SIZE, resultInt);

        mOutput.set_value(new Value(resultBool));

    }

    // Divides the value of input 0 by the value of input 1 and places the result in the output.
    // Parameters:
    // Return value:
    public void divide(){

        int       resultInt;
        boolean[] resultBool;

        resultInt  = Value.toDecimal(mInput0.get_value().get_content()) / Value.toDecimal(mInput1.get_value().get_content());
        resultBool = Value.toBinary(DATA_REGISTER_SIZE, resultInt);

        mOutput.set_value(new Value(resultBool));

    }

    // Divides the value of input 0 by the value of input 1 and places the remainder in the output.
    // Parameters:
    // Return value:
    public void modulo(){

        int       resultInt;
        boolean[] resultBool;

        resultInt  = Value.toDecimal(mInput0.get_value().get_content()) % Value.toDecimal(mInput1.get_value().get_content());
        resultBool = Value.toBinary(DATA_REGISTER_SIZE, resultInt);

        mOutput.set_value(new Value(resultBool));

    }

    /***************BITWISE**OPERATIONS****************/

    // Performs a bitwise AND on both inputs and places the result in the output.
    // Parameters:
    // Return value:
    public void and(){

        boolean[] input0, input1, output;

        input0 = mInput0.get_value().get_content();
        input1 = mInput1.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        for (int i = 0; i < DATA_REGISTER_SIZE; ++i){
            output[i] = input0[i] && input1[i];
        }

        mOutput.set_value(new Value(output));

    }

    // Performs a bitwise OR on both inputs and places the result in the output.
    // Parameters:
    // Return value:
    public void or(){

        boolean[] input0, input1, output;

        input0 = mInput0.get_value().get_content();
        input1 = mInput1.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        for (int i = 0; i < DATA_REGISTER_SIZE; ++i){
            output[i] = input0[i] || input1[i];
        }

        mOutput.set_value(new Value(output));

    }

    // Performs a bitwise XOR on both inputs and places the result in the output.
    // Parameters:
    // Return value:
    public void xor(){

        boolean[] input0, input1, output;

        input0 = mInput0.get_value().get_content();
        input1 = mInput1.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        for (int i = 0; i < DATA_REGISTER_SIZE; ++i){
            output[i] = input0[i] ^ input1[i];
        }

        mOutput.set_value(new Value(output));

    }

    // Performs a bitwise NOT on input 0 and places the result in the output.
    // Parameters:
    // Return value:
    public void complement0(){

        boolean[] input, output;

        input = mInput0.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        for (int i = 0; i < DATA_REGISTER_SIZE; ++i){
            output[i] = !input[i];
        }

        mOutput.set_value(new Value(output));

    }

    // Performs a bitwise NOT on input 1 and places the result in the output.
    // Parameters:
    // Return value:
    public void complement1(){

        boolean[] input, output;

        input = mInput1.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        for (int i = 0; i < DATA_REGISTER_SIZE; ++i){
            output[i] = !input[i];
        }

        mOutput.set_value(new Value(output));

    }

    /****************LOGIC**OPERATIONS*****************/

    // Checks if input 0 is equal to input 1. Sets output to 1 if true and 0 if not.
    // Parameters:
    // Return value:
    public void equal(){

        boolean equal;

        equal  = Value.toDecimal(mInput0.get_value().get_content()) == Value.toDecimal(mInput1.get_value().get_content());

        if (equal){
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 1));
        } else {
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 0));
        }

    }

    // Checks if input 0 is not equal to input 1. Sets output to 1 if true and 0 if not.
    // Parameters:
    // Return value:
    public void notEqual(){

        boolean notEqual;

        notEqual  = Value.toDecimal(mInput0.get_value().get_content()) != Value.toDecimal(mInput1.get_value().get_content());

        if (notEqual){
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 1));
        } else {
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 0));
        }

    }

    // Checks if input 0 is greater than input 1. Sets output to 1 if true and 0 if not.
    // Parameters:
    // Return value:
    public void greaterThan(){

        boolean greaterThan;

        greaterThan  = Value.toDecimal(mInput0.get_value().get_content()) > Value.toDecimal(mInput1.get_value().get_content());

        if (greaterThan){
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 1));
        } else {
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 0));
        }

    }

    // Checks if input 0 is less than input 1. Sets output to 1 if true and 0 if not.
    // Parameters:
    // Return value:
    public void lessThan(){

        boolean lessThan;

        lessThan  = Value.toDecimal(mInput0.get_value().get_content()) < Value.toDecimal(mInput1.get_value().get_content());

        if (lessThan){
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 1));
        } else {
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 0));
        }

    }

    // Checks if input 0 is greater than or equal to input 1. Sets output to 1 if true and 0 if not.
    // Parameters:
    // Return value:
    public void greaterOrEqual(){

        boolean greaterOrEqual;

        greaterOrEqual  = Value.toDecimal(mInput0.get_value().get_content()) >= Value.toDecimal(mInput1.get_value().get_content());

        if (greaterOrEqual){
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 1));
        } else {
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 0));
        }

    }

    // Checks if input 0 is less than or equal to input 1. Sets output to 1 if true and 0 if not.
    // Parameters:
    // Return value:
    public void lessOrEqual(){

        boolean lessOrEqual;

        lessOrEqual  = Value.toDecimal(mInput0.get_value().get_content()) <= Value.toDecimal(mInput1.get_value().get_content());

        if (lessOrEqual){
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 1));
        } else {
            mOutput.set_value(new Value(DATA_REGISTER_SIZE, 0));
        }

    }

    /****************SHIFT**OPERATIONS*****************/

    // Shifts input 0 to the left by the given number of bits. Fills the removed bits with the given value.
    // Parameters: Number of bits, Filler.
    // Return value:
    public void shiftLeft0(int numOfBits, boolean filler){

        boolean[] input, output;
        int       i;

        input = mInput0.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        // Move numOfBits to the left until numOfBits is left at the end.
        for (i = 0; i < (DATA_REGISTER_SIZE - numOfBits); ++i){
            output[i] = input[i + numOfBits];
        }
        // Fill those bits with the filler.
        for (; i < DATA_REGISTER_SIZE; ++i){
            output[i] = filler;
        }

        mOutput.set_value(new Value(output));

    }

    // Shifts input 1 to the left by the given number of bits. Fills the removed bits with the given value.
    // Parameters: Number of bits, Filler.
    // Return value:
    public void shiftLeft1(int numOfBits, boolean filler){

        boolean[] input, output;
        int       i;

        input = mInput1.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        // Move numOfBits to the left until numOfBits is left at the end.
        for (i = 0; i < (DATA_REGISTER_SIZE - numOfBits); ++i){
            output[i] = input[i + numOfBits];
        }
        // Fill those bits with the filler.
        for (; i < DATA_REGISTER_SIZE; ++i){
            output[i] = filler;
        }

        mOutput.set_value(new Value(output));

    }

    // Shifts input 0 to the right by the given number of bits. Fills the removed bits with the given value.
    // Parameters: Number of bits, Filler.
    // Return value:
    public void shiftRight0(int numOfBits, boolean filler){

        boolean[] input, output;
        int       i;

        input = mInput0.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        // Move numOfBits to the left until numOfBits is left at the end.
        for (i = (DATA_REGISTER_SIZE - 1); i >= numOfBits; --i){
            output[i] = input[i - numOfBits];
        }
        // Fill those bits with the filler.
        for (; i >= 0; --i){
            output[i] = filler;
        }

        mOutput.set_value(new Value(output));

    }

    // Shifts input 1 to the right by the given number of bits. Fills the removed bits with the given value.
    // Parameters: Number of bits, Filler.
    // Return value:
    public void shiftRight1(int numOfBits, boolean filler){

        boolean[] input, output;
        int       i;

        input = mInput1.get_value().get_content();
        output = new boolean[DATA_REGISTER_SIZE];

        // Move numOfBits to the left until numOfBits is left at the end.
        for (i = (DATA_REGISTER_SIZE - 1); i >= numOfBits; --i){
            output[i] = input[i - numOfBits];
        }
        // Fill those bits with the filler.
        for (; i >= 0; --i){
            output[i] = filler;
        }

        mOutput.set_value(new Value(output));

    }

}