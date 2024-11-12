package com.cleverthis.interview.connect;

import java.io.*;
import java.net.Socket;

/** * Implement RESTful connector for padlock 
 *  <br/> 
 * <ul>
 * <li>Created PadlockRestConnector class to communicate with the padlock via RESTful API.</li> 
 * <li>Implemented methods to get the number of buttons on the numpad, write input buffer, and check if the passcode is correct.</li>
 * <li>Designed to allow for future extensions and modular communication with the padlock cracker.</li>
 * </ul> 
 * */
public class PadlockTcpConnector implements IPadlockConnector {
    private final String serverAddress;
    private final int serverPort;

    public PadlockTcpConnector(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public int getNumpadSize() {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET_NUMPAD_SIZE");
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException("Failed to get numpad size", e);
        }
    }

    @Override
    public Integer writeInputBuffer(int address, int keyIndex) {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("WRITE_INPUT_BUFFER " + address + " " + keyIndex);
            String response = in.readLine();
            return response.equals("null") ? null : Integer.parseInt(response);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write input buffer", e);
        }
    }

    @Override
    public boolean isPasscodeCorrect() {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("IS_PASSCODE_CORRECT");
            return Boolean.parseBoolean(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException("Failed to check if passcode is correct", e);
        }
    }
}
