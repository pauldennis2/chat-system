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
public class LiveChatClient {

    public static final boolean DEBUG = true;
    public static final int PORT_NUMBER = 8005;
    Scanner userInputScanner;

    public static void main(String[] args) {
        new SimpleChatClient().runChatClient();
    }

    public LiveChatClient() {
        userInputScanner = new Scanner(System.in);
    }

    public void runChatClient () {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter IP address to connect to (use \"localhost\" for self)");
            String response = scanner.nextLine();
            Socket clientSocket = new Socket(response, PORT_NUMBER);

            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.println("Chat Client connected");

            while (true) {
                System.out.println("Input next token to send to server");
                String token = scanner.nextLine();
                if (token.equals("exit")) {
                    outToServer.println("exit");
                    break;
                } else {
                    outToServer.println("Client says " + token);
                }
                String serverResponse = inFromServer.readLine();
                System.out.println("Server says: " + serverResponse);
            }
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
