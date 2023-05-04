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
public class ChildAnt extends Ant {
    
    public ChildAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getChildList().size();
        controller.ant().getChildList().add(this);
    
    }
    
    @Override
    public void run() {
        
        try {
        
            controller.colony().enterColony();

            while(true) {

                checkPaused(controller);



                    controller.colony().enterEatingZone(); // Enters eating zone
                    controller.colony().eat(1); // Eats (taking from 3 to 5 seconds).
                    sleep(3000 + (int) (2000*Math.random()));

                    controller.colony().exitEatingZone();   // Exits eating zone and
                    controller.colony().enterRestingZone(); // enters resting zone.
                    sleep(4000);                            // rests for 4 seconds.

                    execCounter++;
            
            }
        } catch (InterruptedException ie) {
            
            handleThreat();
            
        } catch (ColonyAccessException caex) {
            Logger.getLogger(ChildAnt.class.getName()).log(Level.SEVERE, null, caex);
        }


            

    
    }
    
    public String getAntId() {
        return "HC" + String.format("%04d", antId);
    }
    
    @Override
    public String toString() {
    
        return getAntId();
    
    }
    
    /**
     * This method contains the behaviour {@code ChildAnt} will follow in <br>
     * case of a threat.
     */
    public void handleThreat() {
    
        // Exits any zone within the colony if currently in and moves up
        // to the shelter.
        controller.colony().exitEatingZone();
        controller.colony().exitRestingZone();

        // Enters the shelter and waits for the threat to be eliminated.
        controller.colony().enterShelter();
    
    }
    
}
