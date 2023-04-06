/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonyAccess;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.helpers.Logger;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;

// TEST PASSED

/**
 *
 * @author ssierra
 */
public class ColonyAccessTest {
    
    public static void main(String[] args) throws InterruptedException {
        
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        
        WorkerAnt ant1 = controller.spawnWorkerAnt();
        WorkerAnt ant2 = controller.spawnWorkerAnt();
        WorkerAnt ant3 = controller.spawnWorkerAnt();

        WorkerAnt ant4 = controller.spawnWorkerAnt();

        WorkerAnt ant5 = controller.spawnWorkerAnt();

        
        controller.colony().getColony().getOutside().add(ant1);
        controller.colony().getColony().getOutside().add(ant2);
        controller.colony().getColony().getOutside().add(ant3);
        controller.colony().getColony().getOutside().add(ant4);
        controller.colony().getColony().getOutside().add(ant5);

        Thread.sleep(1000);
        Logger.println("Program starts!", true);
        // This Logger.println function delays the actual timestamp.
        // however, after adding these two lines above, this test
        // Can be considered as passed.

        
        ant1.start();
        ant2.start();
        ant3.start();
        ant4.start();
        ant5.start();

        
        
        
    }
    
}
