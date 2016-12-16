package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by erronius on 12/14/2016.
 */
public class SimpleChatServer implements Runnable {

    public static final int DEFAULT_PORT_NUMBER = 8005;
    public static final boolean DEBUG = true;

    int portNumber;
    PrintWriter outToClient;
    BufferedReader inFromClient;
    Socket clientSocket;
    ServerSocket serverListener;

    public SimpleChatServer () {
        portNumber = DEFAULT_PORT_NUMBER;
    }

    public SimpleChatServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void startServer () {
        try {
            serverListener = new ServerSocket(portNumber);
            Thread serverThread = new Thread(this);
            serverThread.start();
            while (true) {
                String clientResponse = inFromClient.readLine();

                if (clientResponse.startsWith("!unconfirmed")) {
                    String responseConfirmation = "!" + clientResponse.substring(3);
                    sendMessageToClient(responseConfirmation);
                } else if (clientResponse.startsWith("!confirmed")) {

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run () {
        try {
            clientSocket = serverListener.accept();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public boolean sendMessageToClient (String message) {
        if (message.startsWith("!")) {
            System.out.println("Messages cannot start with '!'");
            throw new AssertionError();
        }
        outToClient.println("!unconfirmed " + message);
        try {
            String clientResponse = inFromClient.readLine();

            if (clientResponse.startsWith("!unconfirmed")) {
                String responseConfirmation = "!" + clientResponse.substring(3);
                sendMessageToClient(responseConfirmation);
            } else if (clientResponse.startsWith("!confirmed")) {

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
