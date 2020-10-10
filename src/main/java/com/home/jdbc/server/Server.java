package com.home.jdbc.server;

import com.home.jdbc.dao.PersonDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void readNames(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            System.out.println("server listens on port: " + serverSocket);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("input: " + inputLine);
                PersonDAO.updateName(inputLine);
            }
        } catch (IOException e) {
            System.err.println("server fail: " + e.getMessage());
        }
    }


}
