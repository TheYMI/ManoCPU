package Emulator.Components;

import Emulator.Exceptions.*;
import Emulator.Global.Constants;

/**
 * ***********************
 * Created By: Yuval Tzur
 * Date: 14/12/12
 * Time: 14:44
 * Description: Component is an abstract class that defines the basic details of a component.
 *              Each class defining an emulated hardware component will inherit from this class.
 *              Basic details inherited from this class are ID and Name.
 *              Each component type will have its numerical ID that will be defined as a Global identifier for its name.
 * ***********************
 */

public abstract class Component implements Constants {

    // Members:
    int    mId;
    String mName;

    // Setters:
    protected void set_id(int id) throws NegativeIdException {
        if (id < 0){ // Check for legal ID.
            throw new NegativeIdException("ERROR: Tried to set " + id + " as a component ID!");
        }
        mId = id;
    }

    protected void set_name(String name){
        mName = name;
    }

    // Getters:
    public int get_id(){
        return mId;
    }

    public String get_name(){
        return mName;
    }

}
