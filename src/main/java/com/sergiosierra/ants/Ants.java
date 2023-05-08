/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sergiosierra.ants;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.control.ViewController;
import com.sergiosierra.ants.helpers.Seeder;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.views.ServerView;


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
        serverView.start();
        
       
        
    }
    
     
}
