/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodStorage;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssierra
 */
public class FoodStorageTest {
    public static void main(String[] args) {
        
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        
        for (int i = 0; i < 30; i++) {
        
            try {
                Thread.sleep((int) (2000*Math.random()));
            } catch (InterruptedException ex) {
                Logger.getLogger(FoodStorageTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            new WorkerAnt(controller).start();
        
        }
        
    }
}
