package seeder;


import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.helpers.Seeder;
import com.sergiosierra.ants.models.Colony;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ssierra
 */
public class SeederTest {
    
    public static void main(String[] args) throws InterruptedException {
        
        Controller controller = new Controller(new Colony());
        ControllerListener cl = new ControllerListener(controller);
        Seeder seeder = new Seeder(controller, 10000);
        
        cl.start();
        seeder.start();
        
        Thread.sleep(9000); // Wait 9s before interrupting the seeder;
        controller.pause();
        
        Thread.sleep(10000); // Leave the seeder interrupted for 10s.
        controller.resume();
        
    }
    
}
