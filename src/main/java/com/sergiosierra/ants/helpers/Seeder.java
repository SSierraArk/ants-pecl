/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import com.sergiosierra.ants.interfaces.Pausable;
import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import com.sergiosierra.ants.util.Config;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <b>EN</b>: This class provides methods to generate and start ant threads as per <br>
 * the specification. <br><br>
 * <b>ES</b>: Esta clase proporciona métodos para generar y ejecutar los hilos <br>
 * correspondientes a las hormigas según la especificación.
 * @author ssierra
 */
public class Seeder extends Thread implements Pausable {
    
    /**
     * <b>EN</b>: The main application controller. <br><br>
     * <b>ES</b>: El controlador principal de la aplicación.
     */
    private Controller controller;
    
    /**
     * <b>EN</b>: Total amount of instances to be generated. <br><br>
     * <b>ES</b>: Cantidad total de instancias a ser generadas.
     */
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
                Thread.sleep(Config.SEEDER_MIN + (int) (Config.SEEDER_OFFSET*Math.random()));
                if (workerList.size() % 3 == 0) {

                    controller.spawnSoldierAnt().start();
                    Thread.sleep(Config.SEEDER_MIN + (int) (Config.SEEDER_OFFSET*Math.random()));
                    ChildAnt ant = controller.spawnChildAnt();
                    ant.start();
                    Thread.sleep(Config.SEEDER_MIN + (int) (Config.SEEDER_OFFSET*Math.random()));
                    if (controller.isUnderAttack()) ant.interrupt();
                    i = i + 2;
            
                }
                
        
            } catch (InterruptedException ie) {
            }
        }
        
        
    
    }
    
}
