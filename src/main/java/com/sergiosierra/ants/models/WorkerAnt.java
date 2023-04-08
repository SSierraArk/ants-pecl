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
        this.holdItems(1);
        //Logger.syncPrintln("I'm in! - " + antId, true);
        
        if (antId % 2 == 0) {
        
            try {
                controller.colony().enterEatingZone();
                controller.colony().eat(1);
                sleep(2000 + (int) (2000*Math.random()));
            } catch (Exception e) {
            }
            controller.colony().exitEatingZone();
            controller.colony().exitColony();
            
        } else {
        
            controller.colony().enterEatingZone(1);
            this.dropItems(1);
            try {
                sleep(2000 + (int) (2000*Math.random()));
            } catch (Exception e) {
            }
            controller.colony().exitEatingZone();
            controller.colony().exitColony();
            //Logger.syncPrintln("I'm out! - " + antId, true);

        }
    
    }

    public String getAntId() {
        return "HO" + String.format("%04d", antId);
    }
    
    @Override
    public String toString() {
    
        return getAntId();
    
    }
    
    
    
}
