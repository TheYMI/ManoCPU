package Emulator.Global;

import java.util.ArrayList;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 19:11
 * Description: Value is defines a binary value. It must have a size (Number of bits);
 *              It is used to allow easy manipulation on binary values.
 * ***********************
 */

public class Value implements Constants {

    // Members:
    int       mSize;
    boolean[] mContent;

    // Constructors:
    public Value(int size){
        mSize = size;
        mContent = new boolean[mSize];

        // By default, a binary value is 0.
        for (int i = 0; i < mSize; ++i){
            mContent[i] = _0;
        }
    }

    public Value(boolean[] value){
        mSize = value.length;
        set_content(value); // Uses set_content(boolean[]) to better handle different length values.
    }

    // When given an integer value, the number of bits must be specified.
    public Value(int size, int value){
        mSize = size;
        set_content(value); // Uses set_content(int) to better handle different length values.
    }

    public Value(String value){
        mSize = (value.length() * 4); // Four bits for each hexadecimal digit.
        set_content(value);           // Uses set_content(String) to better handle different length values.
    }

    // Setters:
    public void set_content(boolean[] value){
        // Uses set_content(int) to better handle different length values.
        set_content(Value.toDecimal(value));
    }

    public void set_content(int value){
        // Too small values will have leading zeros.
        // Too big values will be chomped.
        mContent = toBinary(mSize, value);
    }

    public void set_content(String value){
        set_content(Integer.parseInt(value, 16));
    }

    public void set_content(Value value){
        // Uses set_content(boolean[]) to better handle different length values.
        set_content(value.get_content());
    }

    // Getters:
    public int get_size(){
        return mSize;
    }

    public boolean[] get_content(){
        return mContent.clone();
    }

    public int get_decimal(){
        return Value.toDecimal(mContent);
    }

    public String get_hexadecimal(){
        return Value.toHexadecimal(mContent);
    }

    /**************************************************/

    // Static function that converts an integer value to a binary value.
    // Parameters: Number of bits, Integer value.
    // Return value: Binary value in the specified amount of bits.
    public static boolean[] toBinary(int size, int value){

        boolean[] binVal = new boolean[size];

        // Set each bit to it's value by checking the remainder from a division by 2.
        // Runs only as long as the value has information (Not equals to 0). The rest is filled with leading zeros.
        // If the value needs more bits than available, it will be chomped.
        for (int i = (size - 1); (i >= 0) && (value != 0); --i){
            if ((value % 2) == 0){ // Check for the bit's value.
                binVal[i] = _0;
            } else {
                binVal[i] = _1;
            }
            value /= 2; // Move to the next bit.
        }

        return binVal;

    }

    // Static function that converts an hexadecimal (String) value to a binary value.
    // Parameters: Hexadecimal value.
    // Return value: Binary value in the specified amount of bits.
    public static boolean[] toBinary(String value){
        return Value.toBinary((value.length() * 4), Integer.parseInt(value, 16)); // Four bits for each hexadecimal digit.
    }

    // Static function that converts a binary value to an integer value.
    // Parameters: Binary value.
    // Return value: Integer value.
    public static int toDecimal(boolean[] value){

        int intVal = 0;

        // For each bit, if it's a 1, some its decimal value.
        for (int i = (value.length - 1), j = 1; i >= 0; --i, j *= 2){
            if (value[i]){
                intVal += j;
            }
        }

        return intVal;

    }

    // Static function that converts an hexadecimal value to an integer value.
    // Parameters: Hexadecimal value.
    // Return value: Integer value.
    public static int toDecimal(String value){
        return Integer.parseInt(value, 16);
    }

    // Static function that converts a binary value to an hexadecimal value.
    // Parameters: Boolean value.
    // Return value: Hexadecimal value.
    public static String toHexadecimal(boolean[] value){

        String hexVal = "";

        int length = value.length;
        while ((length % 4) != 0){
            ++length;
        }

        value = Value.toBinary(length, Value.toDecimal(value));
        for (int i = (value.length - 1); i >= 0; i -= 4){
            hexVal = Integer.toHexString(Value.toDecimal(new boolean[]{value[i-3], value[i-2], value[i-1], value[i]})).toUpperCase() + hexVal;
        }

        return hexVal;
    }

    // Static function that converts an integer value to an hexadecimal value.
    // Parameters: Number of digits, Integer value.
    // Return value: Hexadecimal value in the specified number of digits.
    public static String toHexadecimal(int size, int value){
        return Value.toHexadecimal(Value.toBinary((size * 4), value));
    }

    // Static function that converts a string of a binary value to an hexadecimal value.
    // Parameters: Boolean string value.
    // Return value: Hexadecimal value.
    public static String toHexadecimal(String value){

        boolean[] binVal = new boolean[value.length()];

        for (int i = 0; i < value.length(); ++i){
            binVal[i] = value.charAt(i) == '1';
        }

        return Value.toHexadecimal(binVal);
    }

    // Converts a binary value to a printable string.
    // Parameters:
    // Return value: String with binary value (With leading zeros, according to size).
    public String toString(){

        String str = "";

        // For each bit, add its value to the string.
        for (int i = 0; i < mSize; ++i){
            if (mContent[i]){
                str += '1';
            } else {
                str += '0';
            }
        }

        return str;

    }

    // Complements the value.
    // Parameters:
    // Return value:
    public void complement(){

        // For each bit, perform a not operation.
        for (int i = 0; i < mSize; ++i){
            mContent[i] = !mContent[i];
        }

    }

    // Adds 1 to the value.
    // Parameters:
    // Return value:
    public void increment(){
        mContent = Value.toBinary(mContent.length, Value.toDecimal(mContent) + 1);
    }

}
