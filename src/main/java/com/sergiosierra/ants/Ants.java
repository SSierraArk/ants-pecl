/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sergiosierra.ants;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.control.ViewController;
import com.sergiosierra.ants.helpers.Seeder;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.views.ServerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;


/**
 *
 * @author ssierra
 */
public class Ants {

    public static void main(String[] args) throws InterruptedException {
        
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        Seeder seeder = new Seeder(controller, 10000);
        seeder.start();
        
        ViewController serverView = new ViewController(ServerView.class);
        
        serverView.attach(controller);
        
        
        serverView.prompt();
        ((JButton) serverView.getComponentByName("pauseExecBtn")).addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.pause();
            }
        
        });
        
        ((JButton) serverView.getComponentByName("restartExecBtn")).addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.resume();
            }
        
        });
        
        ((JButton) serverView.getComponentByName("generateThreatBtn")).addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.startAttack();
            }
        
        });
        serverView.start();
        
       
        
    }
    
     
}
