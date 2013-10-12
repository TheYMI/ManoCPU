package Emulator.Components;

import Emulator.Global.Value;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 17:40
 * Description: This class defines the interface of all the Components that store data.
 *              A component is defined as a data component if it can hold values.
 * ***********************
 */

public interface DataComponent {

    public DataComponent get_input0();
    public DataComponent get_input1();
    public DataComponent get_output();
    public boolean       evaluateAsBoolean();
    public boolean       evaluateAsBoolean(int bitIndex);
    public boolean       evaluateAsBoolean(int bitStart, int bitEnd);
    public boolean       isWritable();
    public boolean       isWritable0();
    public boolean       isWritable1();
    public boolean       changed();
    public Value         get_value();
    public Value         get_value(int index);
    public Value         get_value(int start, int end);
    public Value         get_row(int address);
    public void          set_value(Value value);
    public void          set_value(int index, Value value);
    public void          set_value(int start, int end, Value value);
    public void          enableWrite();
    public void          enableWrite0();
    public void          enableWrite1();
    public void          disableWrite();
    public void          disableWrite0();
    public void          disableWrite1();
    public void          update();
    public void          clear();
    public void          increment();
    public void          set();
    public void          passThrough0();
    public void          passThrough1();
    public void          sum();
    public void          subtract();
    public void          multiply();
    public void          divide();
    public void          modulo();
    public void          and();
    public void          or();
    public void          xor();
    public void          equal();
    public void          notEqual();
    public void          greaterThan();
    public void          lessThan();
    public void          greaterOrEqual();
    public void          lessOrEqual();
    public void          complement();
    public void          complement0();
    public void          complement1();
    public void          shiftLeft0(int numOfBits, boolean filler);
    public void          shiftLeft1(int numOfBits, boolean filler);
    public void          shiftRight0(int numOfBits, boolean filler);
    public void          shiftRight1(int numOfBits, boolean filler);
    public int           get_decimal();
    public int           get_decimal(int bitIndex);
    public int           get_decimal(int bitStart, int bitEnd);

}
