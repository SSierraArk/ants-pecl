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
        
        cl.start();
        Seeder.seed(controller, 10000);
        
    }
    
}
