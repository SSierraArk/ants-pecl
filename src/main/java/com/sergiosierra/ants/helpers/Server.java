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

/**
 * <b>EN</b>: This class provides support for the client-server architecture part <br>
 * of the application. (localhost will be used by default). <br><br>
 * <b>ES</b>: Esta clase proporciona soporte para la parte cliente-servidor de la aplicación. <br>
 * (Se empleará localhost por defecto).
 * @author ssierra
 */
public class Server extends Thread {
    
    /**
     * <b>EN</b>: The application main controller.<br><br>
     * <b>ES</b>: El controlador principal de la aplicación.
     */
    private Controller controller;
    
    /**
     * <b>EN</b>: {@code ServerSocket} object representing the server side of the <br>
     * application.<br><br>
     * <b>ES</b>: Objeto {@code ServerSocket} que representa el lado del servidor de
     * <br> la aplicación.
     */
    private ServerSocket serverSocket;
    
    /**
     * <b>EN</b>: {@code Socket} object representing the client side of the <br>
     * application.<br><br>
     * <b>ES</b>: Objeto {@code Socket} que representa el lado del cliente de
     * <br> la aplicación.
     */
    private Socket clientSocket;
    
    /**
     * <b>EN</b>: Port for the application to be deployed on. <br><br>
     * <b>ES</b>: Puerto por el cual se desplegará la aplicación.
     */
    private int port;
    
    public Server(Controller controller, int port) {
    
        this.controller = controller;
        this.port = port;
        
    }
    
    @Override
    public void run() {
    
        serve(port);
    
    }
    
    /**
     * <b>EN</b>: This method sets everything up for the application to be served <br>
     * and makes the server listen to a certain port passed in as a parameter. <br><br>
     * <b>ES</b>: Este método prepara todo para que la aplicación pueda ser entregada <br>
     * desde el servidor y lo pone en escucha en un determinado puerto que recibe como parámetro.
     * @param port 
     */
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
