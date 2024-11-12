package com.cleverthis.interview.crack;

import com.cleverthis.interview.connect.IPadlockConnector;

/**
 * Implement IPadlockCracker interface for padlock cracking
 * <br/>
 * <ul>
 *     <li>Defined interface method to solve the padlock cracking problem using the provided padlock connector.</li>
 * </ul>
 */
public interface IPadlockCracker {

    public void solve(IPadlockConnector padlockConnector);

}
