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
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author ssierra
 */
public class Seeder extends Thread implements Pausable {
    
    private Controller controller;
    private int totalInstances;
    
    public Seeder(Controller controller, int totalInstances) {
    
        this.controller = controller;
        this.totalInstances = totalInstances;
    
    }
    
    @Override
    public void checkPaused(Controller controller) {
    
        if (controller.isPaused()) {
            
            controller.pauseSem().acquireUninterruptibly();
                
        }
    
    }
    
    @Override
    public void run() {
    
        CopyOnWriteArrayList<ChildAnt> childList = controller.ant().getChildList();
        CopyOnWriteArrayList<SoldierAnt> soldierList = controller.ant().getSoldierList();
        CopyOnWriteArrayList<WorkerAnt> workerList = controller.ant().getWorkerList();

        
        for(int i = 0; i < totalInstances; i++) {
            
            this.checkPaused(controller);
                
            try {
                
            
                controller.spawnWorkerAnt().start();
                System.out.println(workerList.get(workerList.size() - 1).getAntId());
                Thread.sleep(800 + (int) (2700*Math.random()));
                if (workerList.size() % 3 == 0) {

                    controller.spawnSoldierAnt().start();
                    Thread.sleep(800 + (int) (2700*Math.random()));
                    controller.spawnChildAnt().start();
                    Thread.sleep(800 + (int) (2700*Math.random()));
                    i = i + 2;
            
                }
                
        
            } catch (InterruptedException ie) {
            }
        }
        
        
    
    }
    
}
