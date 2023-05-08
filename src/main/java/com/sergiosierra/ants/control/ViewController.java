/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.helpers.Response;
import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

/**
 *
 * @author ssierra
 */
public class ViewController <T extends JFrame> extends Thread {
    
    private JFrame frame;
    private Controller controller;
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
    
    public void attach(Controller controller) {
    
        this.controller = controller;
        
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
            update(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void update(int updateRatioMillis) throws InterruptedException {
    
        while(true) {
        
            Response<String> response = controller.fetch("FETCH//server");
            
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
