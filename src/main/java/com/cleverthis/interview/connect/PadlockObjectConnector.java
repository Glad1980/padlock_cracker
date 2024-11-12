package com.cleverthis.interview.connect;

import com.cleverthis.interview.padlock.PadlockImpl;

/**
 * Implement object connector for padlock
 * <br/>
 * <ul>
 *     <li>Created PadlockObjectConnector class to communicate with the padlock as a Java object.</li>
 *     <li>Implemented methods to get the number of buttons on the numpad, write input buffer, and check if the passcode is correct.</li>
 *     <li>Designed to allow for future extensions and modular communication with the padlock cracker.</li>
 * </ul>
 */
public class PadlockObjectConnector implements IPadlockConnector {
    private PadlockImpl padlock;

    public PadlockObjectConnector(PadlockImpl padlock) {
        this.padlock = padlock;
    }

    @Override
    public int getNumpadSize() {
       return padlock.getNumpadSize();
    }

    @Override
    public Integer writeInputBuffer(int address, int keyIndex) {
        return padlock.writeInputBuffer(address, keyIndex);
    }

    @Override
    public boolean isPasscodeCorrect() {
        return padlock.isPasscodeCorrect();
    }
}

