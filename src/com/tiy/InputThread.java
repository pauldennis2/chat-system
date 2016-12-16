package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by erronius on 12/14/2016.
 */
public class InputThread implements Runnable {

    private Socket socket;
    public InputThread (Socket socket) {
        this.socket = socket;
    }

    public void run () {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                System.out.println("Received message: " + inputLine + " from " + socket.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
