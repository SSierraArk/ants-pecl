/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.exceptions.ColonyAccessException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void getBehaviour() throws InterruptedException, ColonyAccessException {
    
        // Behaviour for odd-id ants
        if (this.antId % 2 != 0) {
        
            // Get out of the colony, if already in and fetch some food items.
            // Takes 4 seconds and then gets back to the colony.
            controller.colony().exitColony();
            this.holdedItems++;
            Thread.sleep(4000);
            controller.colony().enterColony();
            
            // Get to the food storage room and drop the items (2 to 4 seconds)
            // and leave.
            controller.colony().enterFoodStorage(holdedItems--);
            Thread.sleep(2000 + (int) (2000*Math.random()));
            controller.colony().exitFoodStorage();
        
        // Behaviour for even-id ants
        } else {
        
            // Get into the colony
            controller.colony().enterColony();
            controller.colony().enterFoodStorage();
            
            // Pick a food element up taking from 1 to 2 seconds and leave.
            Thread.sleep(1000 + (int) (1000*Math.random()));
            controller.colony().exitFoodStorage(1);
            
            // Walk up to the Eating Zone (takes 1 to 3 seconds), enter, drop items
            // (takes 1 to 2 seconds) and leave.
            Thread.sleep(1000 + (int) (2000*Math.random()));
            controller.colony().enterEatingZone(1);
            Thread.sleep(1000 + (int) (2000*Math.random()));
            controller.colony().exitEatingZone();
            
        }
        
        this.execCounter++;
        
        
    
    }
    
    @Override
    public void run() {
    
        while(true) {
        
            checkPaused(controller);
            
            try {
                
                getBehaviour(); // Run behaviour based on the ant id.
                
                if (this.execCounter == 10) {
                
                    // Gets to the eating zone, eats a food element taking 3 seconds
                    // and leaves.
                    controller.colony().enterEatingZone();
                    controller.colony().eat(1);
                    Thread.sleep(3000);
                    controller.colony().exitEatingZone();
                    
                    // Goes up to the resting zone, takes 1 second resting, leaves and resets
                    // iteration count.
                    controller.colony().enterRestingZone();
                    Thread.sleep(1000);
                    controller.colony().exitRestingZone();
                    this.execCounter = 0;
                    
                
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(WorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ColonyAccessException ex) {
                Logger.getLogger(WorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
            }
        
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
