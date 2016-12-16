package com.tiy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by erronius on 12/14/2016.
 */
public class SimpleServer implements Runnable {

    ServerSocket myServer;

    public void startServer() {
        try {
            myServer = new ServerSocket(8005);
            Thread serverThread = new Thread(this);
            serverThread.start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void run() {
        try {
            Socket clientSocket = myServer.accept();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
