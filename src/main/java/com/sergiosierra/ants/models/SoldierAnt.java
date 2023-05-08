/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.exceptions.ColonyAccessException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssierra
 */
public class SoldierAnt extends Ant {
    
    public static Semaphore mutexThreatSem = new Semaphore(1, true);
    
    
    public SoldierAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getSoldierList().size();
        controller.ant().getSoldierList().add(this);
    
    }
    
    public String getAntId() {
        return "HS" + String.format("%04d", antId);
    }
    
    @Override
    public String toString() {
    
        return getAntId();
    
    }
    
    @Override
    public void run() {
    
        while(true) {
        
            checkPaused(controller);
            
            try {
                
                
                // Enters the colony if not already in.
                controller.colony().enterColony();

                // Checks the execution counter and gets to eat in case it has to.
                // It takes 1 food element.
                if(this.execCounter == 6) {
                
                    controller.colony().enterEatingZone();
                    controller.colony().eat(1);
                    Thread.sleep(3000);
                    controller.colony().exitEatingZone();
                
                }
                
                // Gets to the instruction zone and takes from 2 to 8 seconds over
                // there.
                controller.colony().enterInstructionZone();
                Thread.sleep(2000 + (int)(6000*Math.random()));
                
                // Exit instruction zone
                controller.colony().exitInstructionZone();
                
                // Get into the resting zone and spend 2 seconds there.
                controller.colony().enterRestingZone();
                Thread.sleep(2000);
                controller.colony().exitRestingZone();
                
                // Increase counter
                this.execCounter++;
                
            
            } catch(InterruptedException ie) {
            
                handleThreat();
                
            } catch (ColonyAccessException ex) {
                Logger.getLogger(SoldierAnt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
            
        }
    
    }
    
    public void handleThreat() {
    
        // Checks all rooms within the colony and ensures to remove the ant from
        // them.
        controller.colony().exitEatingZone();
        controller.colony().exitInstructionZone();
        controller.colony().exitRestingZone();
        
        try {
            // Exits the colony
            controller.colony().exitColony();
            // Get cyclicbarrier from controller and await all other ants.
            controller.threatBarrier().await();
            
            Thread.sleep(200000);
            
            // Threat defeated!
            
            mutexThreatSem.acquire();
            if(controller.isUnderAttack()) {
            
                controller.stopAttack();
            
            }
            mutexThreatSem.release();
            
        } catch (InterruptedException ignored) {
            // EXCEPTION IGNORED
            // This block must NOT be reached.
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(SoldierAnt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }
    
}
