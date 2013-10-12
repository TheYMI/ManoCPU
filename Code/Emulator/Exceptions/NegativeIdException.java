package Emulator.Exceptions;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 21:11
 * Description: This exception occurs when a negative number is given as a component ID.
 * ***********************
 */

public class NegativeIdException extends Exception {

    String mMessage = "";

    public NegativeIdException(){}

    public NegativeIdException(String message){
        mMessage = message;
    }

    public String get_message(){
        return mMessage;
    }

}
