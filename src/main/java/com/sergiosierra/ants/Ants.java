/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sergiosierra.ants;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.control.ViewController;
import com.sergiosierra.ants.helpers.Seeder;
import com.sergiosierra.ants.helpers.Server;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.util.Config;
import java.io.IOException;


/**
 *
 * @author ssierra
 */
public class Ants {

    public static void main(String[] args) throws InterruptedException, IOException {
        
        Config conf = new Config();
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        Server server = new Server(controller, Config.PORT);
        Seeder seeder = new Seeder(controller, Config.INSTANCES);
        seeder.start();
        
        server.start();

        
        //ViewController serverView = ViewController.newServerView(controller);
        //serverView.start();
        //serverView.prompt();
        
        ViewController clientView = ViewController.newClientView(controller);
        clientView.start();
        clientView.prompt();
       
        
    }
    
     
}
