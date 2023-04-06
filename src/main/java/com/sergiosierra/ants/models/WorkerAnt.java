/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.helpers.Logger;
import com.sergiosierra.ants.control.Controller;

/**
 *
 * @author ssierra
 */
public class WorkerAnt extends Ant {
    
    
    
    public WorkerAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getWorkerList().size();
        controller.ant().getWorkerList().add(this);
        
        
    }
    
    @Override
    public void run() {
    
        controller.colony().enterColony();
        //Logger.syncPrintln("I'm in! - " + antId, true);
        try {
            sleep(2000 + (int) (1000*Math.random()));
        } catch (Exception e) {
        }
        controller.colony().exitColony();
        //Logger.syncPrintln("I'm out! - " + antId, true);


    
    }

    public String getAntId() {
        return "HO" + String.format("%04d", antId);
    }
    
    
    
}
