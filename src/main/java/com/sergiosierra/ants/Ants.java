/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sergiosierra.ants;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.control.ViewController;
import com.sergiosierra.ants.helpers.Seeder;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.util.Config;


/**
 *
 * @author ssierra
 */
public class Ants {

    public static void main(String[] args) throws InterruptedException {
        
        Config conf = new Config();
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        Seeder seeder = new Seeder(controller, Config.INSTANCES);
        seeder.start();
        
        ViewController serverView = ViewController.newServerView(controller);
        serverView.start();
        serverView.prompt();
        
        
       
        
    }
    
     
}
