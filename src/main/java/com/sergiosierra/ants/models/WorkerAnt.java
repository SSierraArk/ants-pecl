/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssierra
 */
public class WorkerAnt extends Ant {
    
    
    
    public WorkerAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.getWorkerList().size();
        controller.getWorkerList().add(this);
        
    }
    
    @Override
    public void run() {
    
        controller.colony().enterFoodStorage(1);
        try {
            sleep(3000 + (int) (100*Math.random()));
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Formiguiña " + this.getAntId());
        controller.colony().exitFoodStorage(1);

    
    }

    public String getAntId() {
        return "HO" + String.format("%04d", antId);
    }
    
    
    
}
