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
 * <b>EN</b>: Class implementing a worker ant (Ant's subclass). <br><br>
 * <b>ES</b>: Clase que implementa una hormiga obrera. (Subclase de Ant).
 * @author ssierra
 */
public class WorkerAnt extends Ant {
    
    
    /**
     * <b>EN</b>: Creates a new {@code WorkerAnt} instance taking a {@code Controller} <br>
     * as a parameter and adds it to its corresponding data structure within the controller. <br><br>
     * <b>ES</b>: Crea una nueva instancia de {@code WorkerAnt} tomando un {@code Controller} <br>
     * como parámetro y lo añade a la estructura de datos correspondiente dentro del controlador.
     * @param controller 
     */
    public WorkerAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getWorkerList().size();
        controller.ant().getWorkerList().add(this);
        
        
    }
    
    /**
     *
     * <b>EN</b>: Determines {@code WorkerAnt} behaviour based on its {@code antId}. <br><br>
     * <b>ES</b>: Determina el comportamiento principal a seguir por {@code WorkerAnt} <br>
     * en función de su {@code antId}.
     *
     * @throws InterruptedException 
     */
    public void getBehaviour() throws InterruptedException, IOException {
    
        // Behaviour for odd-id ants
        if (this.antId % 2 != 0) {
        
            // Get out of the colony, if already in and fetch some food items.
            // Takes 4 seconds and then gets back to the colony. 
            controller.colony().exitColony();
            
            if (!controller.colony().getColony().getOutside().contains(this)) {
                
                    Log.logln(this.getAntId() + " Left colony.");

                
                }
            
            controller.ant().setLookingForFood(this);
            Log.logln(this.getAntId() + " Looking for food outside...");
            this.holdedItems++;
            Thread.sleep(4000);
            controller.ant().unsetLookingForFood(this);
            Log.logln(this.getAntId() + " Back to the colony...");
            controller.colony().enterColony();
            Log.logln(this.getAntId() + " Colony entered.");
            
            // Get to the food storage room and drop the items (2 to 4 seconds)
            // and leave.
            controller.colony().enterFoodStorage(this.holdedItems);
            Log.logln(this.getAntId() + " Food storage entered, dropping items...");
            this.holdedItems--;
            Thread.sleep(2000 + (int) (2000*Math.random()));
            controller.colony().exitFoodStorage();
            Log.logln(this.getAntId() + " Items dropped, back to labour.");
        
        // Behaviour for even-id ants
        } else {
        
            // Get into the colony
            controller.colony().enterColony();
            Log.logln(this.getAntId() + " Colony entered.");
            controller.colony().enterFoodStorage();
            Log.logln(this.getAntId() + " Food storage entered, picking food up...");
            
            // Pick a food element up taking from 1 to 2 seconds and leave.
            Thread.sleep(1000 + (int) (1000*Math.random()));
            boolean timedout = controller.colony().exitFoodStorage(1, 3);
            if (!timedout) {
            
                controller.ant().setCarryingFood(this);
                Log.logln(this.getAntId() + " Food picked from the storage!.");
                this.holdedItems++;

                // Walk up to the Eating Zone (takes 1 to 3 seconds), enter, drop items
                // (takes 1 to 2 seconds) and leave.
                Thread.sleep(1000 + (int) (2000*Math.random()));
                controller.colony().enterEatingZone(this.holdedItems--);
                controller.ant().unsetCarryingFood(this);
                Log.logln(this.getAntId() + " Entered eating zone and dropped items.");
                
            } else {
                controller.colony().enterEatingZone();
                Log.logln(this.getAntId() + " Entered eating zone, could not get any food.");
            }
            
            Thread.sleep(1000 + (int) (2000*Math.random()));
            controller.colony().exitEatingZone();
            Log.logln(this.getAntId() + " Left eating zone.");
            
        }
        
        this.execCounter++;
        
        
    
    }
    
    /**
     * <b>EN</b>: Main {@code WorkerAnt} behaviour to be executed as a thread. <br><br>
     * <b>ES</b>: Comportamiento principal a seguir por {@code WorkerAnt} y que se <br>
     * ejecutará como un hilo.
     */
    @Override
    public void run() {
    
        while(true) {
        
            checkPaused(controller);
            
            try {
                
                getBehaviour(); // Run behaviour based on the ant id.
                
                if (this.execCounter == Config.ANT_WORKER_MAX_ITERS) {
                
                    // Gets to the eating zone, eats a food element taking 3 seconds
                    // and leaves.
                    controller.colony().enterEatingZone();
                    Log.logln(this.getAntId() + " Grabbing something to eat...");
                    controller.colony().eat(Config.ANT_WORKER_FOOD_CONSUMED);
                    Thread.sleep(Config.ANT_WORKER_EATING_TIME);
                    controller.colony().exitEatingZone();
                    Log.logln(this.getAntId() + " Food was lovely today! Leaving eating zone...");
                    
                    // Goes up to the resting zone, takes 1 second resting, leaves and resets
                    // iteration count.
                    controller.colony().enterRestingZone();
                    Log.logln(this.getAntId() + " Resting zone entered.");
                    Thread.sleep(Config.ANT_WORKER_RESTING_TIME);
                    controller.colony().exitRestingZone();
                    Log.logln(this.getAntId() + " Left resting zone, back to work!");
                    this.execCounter = 0;
                    
                
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(WorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    
    }

    /**
     * <b>EN</b>: Returns the {@code antId} formatted as per its type. <br><br>
     * <b>ES</b>: Devuelve el {@code antId} formateado según su tipo.
     * @return 
     *          String {@code antId} 
     */
    public String getAntId() {
        return "HO" + String.format("%04d", antId);
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
