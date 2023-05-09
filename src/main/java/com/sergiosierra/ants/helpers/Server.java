/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import com.sergiosierra.ants.control.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssierra
 */
public class Server extends Thread {
    
    private Controller controller;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;
    
    public Server(Controller controller, int port) {
    
        this.controller = controller;
        this.port = port;
        
    }
    
    @Override
    public void run() {
    
        serve(port);
    
    }
    
    public void serve(int port) {
    
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Connection started -> " + serverSocket.getLocalSocketAddress());
            clientSocket = serverSocket.accept();
            System.out.println("Incomming connection from -> " + clientSocket.getInetAddress());
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            
            while (true) {
            
                
                String request = input.readLine();

                if(request.equals("CONNECTION//close")) break;
                
                Response<String> response = controller.query(request);
                output.writeObject(response);
                output.flush();
                
                
            }
            
            output.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
