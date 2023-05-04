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
public class SoldierAnt extends Ant {
    
    
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
        } catch (InterruptedException ex) {
            // This thread should not be interrupted twice while the colony is
            // in danger.
            Thread.interrupted(); // Ignore 
        }
        
        

    }
    
}
