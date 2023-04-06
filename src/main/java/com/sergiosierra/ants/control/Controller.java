/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.ArrayList;

/**
 *
 * @author ssierra
 */
public class Controller {
    

    private ColonyController colonyController;
    private AntController antController;
    
    public Controller(Colony colony) {
        
        colonyController = new ColonyController(colony);
        antController = new AntController();
        
    }
    
    // Factory methods
    
    public WorkerAnt spawnWorkerAnt() {
    
        return new WorkerAnt(this);
        
    }
    
    
    public ColonyController colony() {
    
        return colonyController;
    
    }
    
    
    public AntController ant() {
    
        return antController;
        
    }
    
}
