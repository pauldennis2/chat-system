package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by erronius on 12/14/2016.
 */
public class SimpleChatClient {

    public static final boolean DEBUG = true;
    public static final int PORT_NUMBER = 8005;
    Scanner userInputScanner;
    PrintWriter outToServer;
    BufferedReader inFromServer;

    public static void main(String[] args) {
        new SimpleChatClient().runChatClient();
    }

    public SimpleChatClient() {
        userInputScanner = new Scanner(System.in);
    }

    public void runChatClient () {
        try {
            Socket clientSocket = new Socket ("localhost", PORT_NUMBER);

            outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.println("Chat Client connected");

            /*while (true) {
                String token = getInputFromUser();
                if (token.equals("exit")) {
                    break;
                } else {
                    outToServer.println("Client says " + token);
                }
                String serverResponse = inFromServer.readLine();
                System.out.println("Server says: " + serverResponse);
            }*/
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*public void pingServer () {
        outToServer.println("!unconfirmed ping");
        try {
            String serverResponse = inFromServer.readLine();
            System.out.println("Server's response");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/

    public void sendMessageToServer (String message) {
        if (message.startsWith("!")) {
            System.out.println("Messages cannot start with '!'");
            throw new AssertionError();
        }
        outToServer.println("!unconfirmed " + message);
        try {
            String serverResponse = inFromServer.readLine();
            System.out.println("Server's response = " + serverResponse);
            if (serverResponse.startsWith("!unconfirmed")) {
                String responseConfirmation = "!" + serverResponse.substring(3);
            } else if (serverResponse.startsWith("!confirmed")) {
                //I got my message back
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
