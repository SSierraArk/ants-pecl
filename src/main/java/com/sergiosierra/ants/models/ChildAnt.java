/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.exceptions.ColonyAccessException;
import com.sergiosierra.ants.helpers.Log;
import java.io.IOException;
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
            
            boolean logToFile = false;
            controller.colony().enterColony();

            while(true) {

                checkPaused(controller);



                    controller.colony().enterEatingZone(); // Enters eating zone
                    Log.logln(this.getAntId() + " Eating zone entered.", logToFile);
                    
                    controller.colony().eat(1); // Eats (taking from 3 to 5 seconds).
                    Log.logln(this.getAntId() + " Eating...", logToFile);
                    sleep(3000 + (int) (2000*Math.random()));
                    Log.logln(this.getAntId() + " Ended eating...", logToFile);
                    controller.colony().exitEatingZone();   // Exits eating zone and
                    
                    controller.colony().enterRestingZone(); // enters resting zone.
                    Log.logln(this.getAntId() + " Entered, resting zone. Going to rest...", logToFile);
                    
                    sleep(4000);                            // rests for 4 seconds.

                    Log.logln(this.getAntId() + " Woke up!", logToFile);
                    
                    controller.colony().exitRestingZone();
                    
                    execCounter++;
            
            }
        } catch (InterruptedException ie) {
            
            handleThreat();
            
        } catch (ColonyAccessException caex) {
            Logger.getLogger(ChildAnt.class.getName()).log(Level.SEVERE, null, caex);
        } catch (IOException ex) {
            Logger.getLogger(ChildAnt.class.getName()).log(Level.SEVERE, null, ex);
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
        controller.threatSem().acquireUninterruptibly();
    
    }
    
}
