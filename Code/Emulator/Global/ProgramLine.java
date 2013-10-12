package Emulator.Global;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 15/12/12
 * Time: 14:09
 * Description: This class defines a line in the program.
 *              Each line will be copied to the memory.
 *              Each line has its value and the address it should have in the memory.
 * ***********************
 */

public class ProgramLine implements Constants {

    // Members:
    Value  mAddress = new Value(ADDR_REGISTER_SIZE);
    Value  mContent = new Value(DATA_REGISTER_SIZE);
    String mMetaCommand;

    // Constructor:
    public ProgramLine(Value address, Value content, String metaCommand){
        mAddress.set_content(address);
        mContent.set_content(content);
        mMetaCommand = metaCommand;
    }

    // Getters:
    public Value get_address(){
        return mAddress;
    }

    public Value get_content(){
        return mContent;
    }

    public String get_metaCommand(){
        return mMetaCommand;
    }

}
