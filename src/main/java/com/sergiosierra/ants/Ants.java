/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sergiosierra.ants;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.control.ViewController;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.views.ServerView;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author ssierra
 */
public class Ants {

    public static void main(String[] args) {
        
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        ViewController serverView = new ViewController(ServerView.class);
        
        serverView.prompt();
        // Explore component list
        Component[] iterable = ((JPanel) serverView.getFrame().getContentPane().getComponents()[0]).getComponents();
        
        for (Component elem : iterable) {
        
            System.out.println("Elem -> " + elem.toString() + "\n");
            
        }
        
        serverView.getTextFieldByName("antsInFoodStorageText").setText("I'm working!");
        serverView.getTextFieldByName("antsCollectingFoodText").setText("I'm working!");

        
    }
    
     
}
