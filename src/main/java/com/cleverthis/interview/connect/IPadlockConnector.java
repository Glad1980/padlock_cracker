package com.cleverthis.interview.connect;


/**
 * Define IPadlockConnector interface for padlock connectors
 *
 * <br/>
 * <ul>
 *     <li>Defined interface methods for getting the number of buttons on the numpad.</li>
 *     <li>Defined interface methods for writing to the input buffer of the padlock.</li>
 *     <li>Defined interface methods for checking if the passcode is correct.</li>
 * </ul>
 */
public interface IPadlockConnector {

    public int getNumpadSize();
    public Integer writeInputBuffer(int address, int keyIndex);
    public boolean isPasscodeCorrect();
    
}
