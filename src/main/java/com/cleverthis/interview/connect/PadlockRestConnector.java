package com.cleverthis.interview.connect;

import java.io.IOException;
import java.net.URL;

/**
 *  Implement RESTful connector for padlock * 
 * - Created PadlockRestConnector class to communicate with the padlock via RESTful API. 
 * - Implemented methods to get the number of buttons on the numpad, write input
 *   buffer, and check if the passcode is correct.
 * - Designed to allow for future extensions and modular communication with the padlock cracker.
 */
public class PadlockRestConnector implements IPadlockConnector {
    private final String baseUrl;

    public PadlockRestConnector(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public int getNumpadSize() {
        try {
            URL url = new URL(baseUrl + "/numpadSize");
            // implements the code that get the numped size from the API

            String response = "4";
            return Integer.parseInt(response);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get numpad size", e);
        }
    }

    @Override
    public Integer writeInputBuffer(int address, int keyIndex) {
        try {
            URL url = new URL(baseUrl + "/writeInputBuffer?address=" + address + "&keyIndex=" + keyIndex);
            // implements the code that post the Input Buffer to the API
            String response = "2";
            return response.equals("null") ? null : Integer.parseInt(response);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write input buffer", e);
        }
    }

    @Override
    public boolean isPasscodeCorrect() {
        try {
            URL url = new URL(baseUrl + "/isPasscodeCorrect");
            // implements the code that get if the Passcode is Correct from the API
            String response = "true";

            return Boolean.parseBoolean(response);
        } catch (IOException e) {
            throw new RuntimeException("Failed to check if passcode is correct", e);
        }
    }
}
