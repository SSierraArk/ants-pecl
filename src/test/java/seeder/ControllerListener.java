package seeder;


import com.sergiosierra.ants.control.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ssierra
 */
public class ControllerListener extends Thread {
    
    private Controller controller;
    
    public ControllerListener(Controller controller) {
    
        this.controller = controller;
    
    }
    
    @Override
    public void run() {
    
        while (true) {
        
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControllerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Worker: " + controller.ant().getWorkerList());
            System.out.println("Child: " + controller.ant().getChildList().size());
            System.out.println("Soldier: " + controller.ant().getSoldierList().size());

        
        }
        
    }
    
}
