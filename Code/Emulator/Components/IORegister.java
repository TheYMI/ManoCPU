package Emulator.Components;

import Emulator.Exceptions.NegativeIdException;
import Emulator.Global.*;
import javax.swing.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 22:52
 * Description: This is an input/output register. It has all the functionality of a register.
 *              This register's size is defined by IO_REGISTER_SIZE.
 * ***********************
 */

public class IORegister extends Register {

    // Constructor:
    public IORegister(int id, String name){
        // Set register size to the input/output register size.
        mValue = new Value(IO_REGISTER_SIZE);
        mBuffer = new Value(IO_REGISTER_SIZE);

        try{ // A negative (illegal) ID will throw a NegativeId exception.
            set_id(id);
        } catch (NegativeIdException ni){
            JOptionPane.showMessageDialog(null, ni.get_message());
        }
        set_name(name);
    }
}
