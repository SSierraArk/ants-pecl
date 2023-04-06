/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.ArrayList;

/**
 *
 * @author ssierra
 */
public class Seeder {
    
    public static void seed(Controller controller, int totalInstances) throws InterruptedException {
    
        ArrayList<ChildAnt> childList = controller.ant().getChildList();
        ArrayList<SoldierAnt> soldierList = controller.ant().getSoldierList();
        ArrayList<WorkerAnt> workerList = controller.ant().getWorkerList();

        
        for(int i = 0; i < totalInstances; i++) {
        
            controller.spawnWorkerAnt();
            System.out.println(workerList.get(workerList.size() - 1).getAntId());
            Thread.sleep(800 + (int) (2700*Math.random()));
            if (workerList.size() % 3 == 0) {
            
                controller.spawnSoldierAnt();
                Thread.sleep(800 + (int) (2700*Math.random()));
                controller.spawnChildAnt();
                Thread.sleep(800 + (int) (2700*Math.random()));
                i = i + 2;
            
            }            
        }
        
        
    
    }
    
}
