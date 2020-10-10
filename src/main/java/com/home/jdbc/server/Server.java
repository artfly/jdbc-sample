package com.home.jdbc.server;

import com.home.jdbc.dao.PersonDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends BaseServer {

    public static void readNames(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            System.out.println("server listens on port: " + serverSocket);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while ((input = in.readLine()) != null) {
                PersonDAO.updateName(BaseServer.input);
            }
        } catch (IOException e) {
            System.err.println("server fail: " + e.getMessage());
        }
    }


}
