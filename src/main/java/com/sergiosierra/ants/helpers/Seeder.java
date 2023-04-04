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
    
        ArrayList<ChildAnt> childList = controller.getChildList();
        ArrayList<SoldierAnt> soldierList = controller.getSoldierList();
        ArrayList<WorkerAnt> workerList = controller.getWorkerList();

        
        for(int i = 0; i < totalInstances; i++) {
        
            workerList.add(new WorkerAnt(controller));
            System.out.println(workerList.get(workerList.size() - 1).getAntId());
            Thread.sleep(800 + (int) (2700*Math.random()));
            if (workerList.size() % 3 == 0) {
            
                soldierList.add(new SoldierAnt());
                Thread.sleep(800 + (int) (2700*Math.random()));
                childList.add(new ChildAnt());
                Thread.sleep(800 + (int) (2700*Math.random()));
                i = i + 2;
            
            }            
        }
        
        
    
    }
    
}
