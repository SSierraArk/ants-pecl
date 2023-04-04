/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodStorage;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;

/**
 *
 * @author ssierra
 */
public class FoodStorageTest {
    public static void main(String[] args) {
        
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        
        for (int i = 0; i < 30; i++) {
        
            new WorkerAnt(controller).start();
        
        }
        
    }
}
