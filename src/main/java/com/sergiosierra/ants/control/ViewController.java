/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.helpers.Response;
import com.sergiosierra.ants.util.Config;
import com.sergiosierra.ants.views.ClientView;
import com.sergiosierra.ants.views.ServerView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

/**
 * <b>EN</b>: This class provides view-specific control logic and acts as a wrapper <br>
 * for these as well. It also includes some ready-to-use factory methods. <br><br>
 * <b>ES</b>: Esta clase proporciona lógica de control relacionada a las vistas y actúa <br>
 * como envoltura también. Además, incluye algunos métodos de factoría listos para usar.
 * @author ssierra
 */
public class ViewController <T extends JFrame> extends Thread {
    
    private JFrame frame;
    private Controller controller;
    private Socket clientSocket;
    private PrintWriter socketOut;
    private ObjectInputStream socketIn;
    private HashMap<String, Component> componentMap = new HashMap<>();
    
    
    /**
     * Saves and structures all components into a {@code HashMap<String, JComponent>} <br>
     * PRE: All components within the view must be placed over (inside) a <br>
     * parent {@code JPanel}
     */
    private void initComponentMap() {

        
        for(Component elem : ((JPanel) frame.getContentPane().getComponents()[0]).getComponents()) {

            String name = "";
            
            if (elem.getName() != null && elem.getName().endsWith("ScrollPane")) {
            
                JViewport viewport = ((JScrollPane) elem).getViewport();
                elem = viewport.getComponents()[0];
                name = elem.getName();
            
            }

            if (elem.getName() != null) {
            
                name = elem.getName();
                
            }
            componentMap.put(name, elem);
            
        }
        
    }
    
    public ViewController(Class<T> frameClass) {

        // Set look and feel;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
            this.frame = frameClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponentMap();
        
        
    
    }
    
    /**
     * Factory method. Returns a ViewController with all configurations made for <br>
     * for an underlying {@code ServerView}.
     * @return 
     */
    public static ViewController newServerView(Controller controller) {
        
        ViewController serverView = new ViewController(ServerView.class);
        
        serverView.attach(controller);
        
        
        ((JButton) serverView.getComponentByName("pauseExecBtn")).addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.query("COMMAND//pause");
            }
        
        });
        
        ((JButton) serverView.getComponentByName("restartExecBtn")).addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.query("COMMAND//resume");
            }
        
        });
        
        ((JButton) serverView.getComponentByName("generateThreatBtn")).addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.query("COMMAND//attack");
            }
        
        });

        return serverView;
    }
    
    public static ViewController newClientView(String ip, int port) {
        
        ViewController clientView = new ViewController(ClientView.class);
        // No controller to be attached this time as connection will take place
        // remotely.
        clientView.attach(ip, port);
        try {
            clientView.socketOut = new PrintWriter(clientView.clientSocket.getOutputStream(), true);
            clientView.socketIn = new ObjectInputStream(clientView.clientSocket.getInputStream());
            //Response<String> response = (Response<String>) input.readObject();

            ((JButton) clientView.getComponentByName("generateThreatRemoteBtn")).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    clientView.socketOut.println("COMMAND//attack");
                    
                }

            });
        
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientView;
    }
    
    public void attach(Controller controller) {
    
        this.controller = controller;
        
    }
    
    public void attach(String ip, int port) {
    
        this.controller = null; // Remote connection.
        try {
            this.clientSocket = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public HashMap<String, Component> getComponentMap() {
        return componentMap;
    }
    
    
    public Component getComponentByName(String name) {
    
        return componentMap.get(name);
        
    }
    
    public JTextField getTextFieldByName(String name) {
    
        return (JTextField) componentMap.get(name);
        
    }
    
    public ArrayList<JTextField> getAllTextFields() {
    
        ArrayList<JTextField> result =  new ArrayList<>();
        
        componentMap.forEach(
        (String key, Component value) -> {
        
            if(key.matches("(.*)Text")) {
            
                result.add((JTextField) value);
                
            }
        
        });
        
        return result;
        
    }
    
    @Override
    public void run() {
    
        try {
            update(Config.UPDATE_RATE);
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void update(int updateRatioMillis) throws InterruptedException, IOException, ClassNotFoundException {
    
        while(true) {
        
            if (clientSocket != null) this.socketOut.println("FETCH//client");
            
            Response<String> response = controller != null
                ? controller.query("FETCH//server")
                : (Response<String>) socketIn.readObject();
                    
            response.getPayload().forEach((key, value) -> {

                if(getComponentByName(key) != null && key.endsWith("Text")) {
                    getTextFieldByName(key).setText(value);
                    
                }
                
                if(getComponentByName(key) != null && key.endsWith("Area")) {
                
                    ((JTextArea) getComponentByName(key)).setText(value);
                    
                }
                
            });
            Thread.sleep(updateRatioMillis);
            
        }
        
    
    }

    public JFrame getFrame() {
        return frame;
    }

    public void prompt() {
        frame.setVisible(true);
  
    }
    
    
    
}
