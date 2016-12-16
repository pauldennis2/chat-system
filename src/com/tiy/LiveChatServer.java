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
public class LiveChatServer {

    public static final int PORT_NUMBER = 8005;
    public static final boolean DEBUG = true;

    Scanner userInputScanner;

    public LiveChatServer() {
        userInputScanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
//        new SimpleChatServer().runServerClient();
    }

    public void runServerClient () {
        try {
            ServerSocket serverListener = new ServerSocket(PORT_NUMBER);
            System.out.println("Listening on port " + PORT_NUMBER);
            Socket clientSocket = serverListener.accept();

            System.out.println("Host address " + clientSocket.getInetAddress().getHostAddress());
            System.out.println("Connection established on ze server.");

            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread outputThread;
            Thread inputThread;

            String inputLine;
            while ((inputLine = inputFromClient.readLine()) != null) {
                System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
                System.out.println("Enter message to send to client:");
                String token = userInputScanner.nextLine();
                if (token.equals("exit")) {
                    outputToClient.println("exit");
                    break;
                } else {
                    outputToClient.println("Server says " + token);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
