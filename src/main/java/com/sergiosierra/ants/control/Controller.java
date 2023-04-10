/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.concurrent.Semaphore;


/**
 *
 * @author ssierra
 */
public class Controller {
    

    private ColonyController colonyController;
    private AntController antController;
    private Semaphore pauseSem = new Semaphore(0, true);
    private boolean isPaused = false;
    
    public Controller(Colony colony) {
        
        colonyController = new ColonyController(colony);
        antController = new AntController();
        
    }
    
    // Factory methods
    
    public WorkerAnt spawnWorkerAnt() {
    
        WorkerAnt ant = new WorkerAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;
        
    }
    
    public SoldierAnt spawnSoldierAnt() {
    
        SoldierAnt ant = new SoldierAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;    
    }
    
    public ChildAnt spawnChildAnt() {
    
        ChildAnt ant = new ChildAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;    
    }
    
    
    public ColonyController colony() {
    
        return colonyController;
    
    }
    
    
    public AntController ant() {
    
        return antController;
        
    }
    
    public Semaphore pauseSem() {
    
        return pauseSem;
        
    }
    
    public boolean isPaused() {
    
        return isPaused;
    
    }
    
    public void pause() {
    
        this.isPaused = true;
    
    }
    
    public void resume() {
    
        pauseSem.release(pauseSem.getQueueLength());
        this.isPaused = false;
    
    }
    
}
