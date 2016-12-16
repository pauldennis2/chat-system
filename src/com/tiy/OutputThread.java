package com.tiy;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by erronius on 12/14/2016.
 */
public class OutputThread implements Runnable {

    private Socket socket;
    public OutputThread (Socket socket) {
        this.socket = socket;
    }

    public void run () {
        //PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
    }
}
