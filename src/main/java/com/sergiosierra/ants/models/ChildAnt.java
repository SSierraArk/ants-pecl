/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.helpers.Log;
import com.sergiosierra.ants.util.Config;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>EN</b>: Class implementing a child ant (Ant's subclass). <br><br>
 * <b>ES</b>: Clase que implementa una hormiga cría. (Subclase de Ant).
 * @author ssierra
 */
public class ChildAnt extends Ant {
    
    /**
     * <b>EN</b>: Creates a new {@code ChildAnt} instance taking a {@code Controller} <br>
     * as a parameter and adds it to its corresponding data structure within the controller. <br><br>
     * <b>ES</b>: Crea una nueva instancia de {@code ChildAnt} tomando un {@code Controller} <br>
     * como parámetro y lo añade a la estructura de datos correspondiente dentro del controlador.
     * @param controller 
     */
    public ChildAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getChildList().size();
        controller.ant().getChildList().add(this);
    
    }
    
    /**
     * <b>EN</b>: Main {@code ChildAnt} behaviour to be executed as a thread. <br><br>
     * <b>ES</b>: Comportamiento principal a seguir por {@code ChildAnt} y que se <br>
     * ejecutará como un hilo.
     */
    @Override
    public void run() {
        
        while(true) {
            
            checkPaused(controller);
            

            try {

                controller.colony().enterColony();
                
                // Logs only if not already within the colony.
                if (!controller.colony().getColony().getInside().contains(this)) {
                
                    Log.logln(this.getAntId() + " Colony entered.");

                
                }
                
                controller.colony().enterEatingZone(); // Enters eating zone
                Log.logln(this.getAntId() + " Eating zone entered.");

                controller.colony().eat(Config.ANT_CHILD_FOOD_CONSUMED); // Eats (taking from 3 to 5 seconds).
                Log.logln(this.getAntId() + " Eating...");
                sleep(Config.ANT_CHILD_EATING_TIME + (int) (Config.ANT_CHILD_EATING_TIME_OFFSET*Math.random()));
                Log.logln(this.getAntId() + " Ended eating...");
                controller.colony().exitEatingZone();   // Exits eating zone and
                Log.logln(this.getAntId() + " Left eating zone.");
                
                controller.colony().enterRestingZone(); // enters resting zone.
                Log.logln(this.getAntId() + " Entered resting zone. Going to rest...");

                sleep(Config.ANT_CHILD_RESTING_TIME); // rests for 4 seconds.

                Log.logln(this.getAntId() + " Woke up!");

                controller.colony().exitRestingZone();
                Log.logln(this.getAntId() + " Left resting zone.");

                execCounter++;
            
            } catch (InterruptedException ie) {

                handleThreat();
                Thread.interrupted();

            } catch (IOException ex) {
                Logger.getLogger(ChildAnt.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }
    
    /**
     * <b>EN</b>: This method contains the algorithm to be followed in case a threat <br>
     * is present. <br>
     * Any {@code InterruptedException} thrown within this method will be IGNORED <br>
     * as this method cannot be executed more than once at a time. <br><br>
     * <b>ES</b>: Este método contiene el algoritmo que se seguirá en caso de una <br>
     * amenaza. <br>
     * Cualquier {@code InterruptedException} lanzada desde este método será <br>
     * IGNORADA ya que este método no se puede ejecutar más de una vez al mismo <br>
     * tiempo.
     */
    public void handleThreat() {
    
        try {
            // Exits any zone within the colony if currently in and moves up
            // to the shelter.
            controller.colony().exitEatingZone();
            controller.colony().exitRestingZone();
            Log.logln(this.getAntId() + " Oh no! there is a threat.");
            // Enters the shelter and waits for the threat to be eliminated.
            controller.colony().enterShelter();
            Log.logln(this.getAntId() + " Protecting at the shelter!");
            controller.threatSem().acquireUninterruptibly();
            controller.colony().exitShelter();
            Log.logln(this.getAntId() + " Threat is gone, back to normal.");
            
        } catch (IOException ex) {
            Logger.getLogger(ChildAnt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <b>EN</b>: Returns the {@code antId} formatted as per its type. <br><br>
     * <b>ES</b>: Devuelve el {@code antId} formateado según su tipo.
     * @return 
     *          String {@code antId} 
     */
    public String getAntId() {
        return "HC" + String.format("%04d", antId);
    }
    
    /**
     * <b>EN</b>: Returns the {@code antId} formatted as per its type. <br>
     * wraps {@code getAntId()} method. <br><br>
     * <b>ES</b>: Devuelve el {@code antId} formateado según su tipo. <br>
     * envuelve el método {@code getAntId()}
     * @return 
     *          String {@code antId} 
     */
    @Override
    public String toString() {
    
        return getAntId();
    
    }
    
}
