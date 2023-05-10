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
        
        // Needed instances initialisation.
        
        Config conf = new Config();
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        Server server = new Server(controller, Config.PORT);
        Seeder seeder = new Seeder(controller, Config.INSTANCES);
        
        // Starting both the seeder and server.
        
        seeder.start();
        server.start();
        
        // SERVER-SIDE BLOCK
        /**********************************************************************
        **********************************************************************/
        // UNCOMMENT BELOW TO TRY THE SERVER-SIDE OF THE APPLICATION.
        /**********************************************************************
        **********************************************************************/
        
        
        ViewController serverView = ViewController.newServerView(controller);
        serverView.start();
        serverView.prompt();
        
        /**********************************************************************
        **********************************************************************/
        // SERVER-SIDE BLOCK ENDS
        
        
        // CLIENT-SIDE BLOCK
        /**********************************************************************
        **********************************************************************/
        // UNCOMMENT BELOW TO TRY THE CLIENT-SIDE OF THE APPLICATION.
        /**********************************************************************
        **********************************************************************/
        
        //ViewController clientView = ViewController.newClientView(controller);
        // clientView.start();
        // clientView.prompt();
        
        /**********************************************************************
        **********************************************************************/
        // CLIENT-SIDE BLOCK ENDS
        
    }
    
     
}
