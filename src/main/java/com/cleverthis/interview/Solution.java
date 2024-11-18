package com.cleverthis.interview;

import com.cleverthis.interview.connect.PadlockObjectConnector;
import com.cleverthis.interview.connect.PadlockRestConnector;
import com.cleverthis.interview.connect.IPadlockConnector;
import com.cleverthis.interview.crack.IPadlockCracker;
import com.cleverthis.interview.crack.PadlockCracker;
import com.cleverthis.interview.padlock.PadlockImpl;

/**
 * Implement Solution class for solving padlock using different connectors
 * <br/>
 * <ul>
 *     <li>Created Solution class to solve padlock using different connectors.</li>
 *     <li>Implemented methods to solve padlock using Java object connector.</li>
 *     <li>Implemented methods to solve padlock using RESTful API connector.</li>
 *     <li>Here, you can add any methods to solve padlock using various connector.</li>
 * </ul>
 */
public class Solution {

    IPadlockCracker padlockCracker = new PadlockCracker();

    // Java object connector, to solve padlock
    public void solve(PadlockImpl padlock) {
        IPadlockConnector padlockObjectCon = new PadlockObjectConnector(padlock);
        padlockCracker.solve(padlockObjectCon);
    }

    // RESTful API connector example
    public void solve(String uri) {
        IPadlockConnector padlockObjectCon = new PadlockRestConnector(uri);
        padlockCracker.solve(padlockObjectCon);
    }
}


    
 
