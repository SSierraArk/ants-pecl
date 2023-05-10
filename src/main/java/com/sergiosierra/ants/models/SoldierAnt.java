/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.helpers.Log;
import com.sergiosierra.ants.util.Config;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>EN</b>: Class implementing a soldier ant (Ant's subclass). <br><br>
 * <b>ES</b>: Clase que implementa una hormiga soldado. (Subclase de Ant).
 * @author ssierra
 */
public class SoldierAnt extends Ant {
    
    /**
     * <b>EN</b>: This binary fair {@code Semaphore} will make all soldier ants set <br>
     * the controller's {@code underAttack} flag to {@code false} in mutual exclusion. <br><br>
     * <b>ES</b>: Este {@code Semaphore} binario y justo hará que las hormigas soldado <br>
     * fijen el marcador de estado {@code underAttack} del controlador a {@code false} <br>
     * en exclusión mutua.
     */
    public static Semaphore mutexThreatSem = new Semaphore(1, true);
    
    /**
     * <b>EN</b>: Creates a new {@code SoldierAnt} instance taking a {@code Controller} <br>
     * as a parameter and adds it to its corresponding data structure within the controller. <br><br>
     * <b>ES</b>: Crea una nueva instancia de {@code SoldierAnt} tomando un {@code Controller} <br>
     * como parámetro y lo añade a la estructura de datos correspondiente dentro del controlador.
     * @param controller 
     */
    public SoldierAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getSoldierList().size();
        controller.ant().getSoldierList().add(this);
    
    }
    
    
    /**
     * <b>EN</b>: Main {@code SoldierAnt} behaviour to be executed as a thread. <br><br>
     * <b>ES</b>: Comportamiento principal a seguir por {@code SoldierAnt} y que se <br>
     * ejecutará como un hilo.
     */
    @Override
    public void run() {
    
        while(true) {
        
            checkPaused(controller);
            
            try {
                
                
                // Enters the colony if not already in.
                controller.colony().enterColony();
                
                if (!controller.colony().getColony().getInside().contains(this)) {
                
                    Log.logln(this.getAntId() + " Colony entered.");

                
                }

                // Checks the execution counter and gets to eat in case it has to.
                // It takes 1 food element.
                if(this.execCounter == Config.ANT_SOLDIER_MAX_ITERS) {
                
                    controller.colony().enterEatingZone();
                    Log.logln(this.getAntId() + " Eating zone entered, time to eat!");
                    controller.colony().eat(Config.ANT_SOLDIER_FOOD_CONSUMED);
                    Thread.sleep(Config.ANT_SOLDIER_EATING_TIME);
                    controller.colony().exitEatingZone();
                    Log.logln(this.getAntId() + " Food was lovely today! Leaving eating zone...");
                
                }
                
                // Gets to the instruction zone and takes from 2 to 8 seconds over
                // there.
                controller.colony().enterInstructionZone();
                Log.logln(this.getAntId() + " Instruction zone entered!");
                Thread.sleep(Config.ANT_SOLDIER_TRAINING_TIME + (int)(Config.ANT_SOLDIER_TRAINING_TIME_OFFSET*Math.random()));
                
                // Exit instruction zone
                controller.colony().exitInstructionZone();
                Log.logln(this.getAntId() + " Left instruction zone, stronger than ever!");
                
                // Get into the resting zone and spend 2 seconds there.
                controller.colony().enterRestingZone();
                Log.logln(this.getAntId() + " Resting zone entered, let's sleep!");
                Thread.sleep(Config.ANT_SOLDIER_RESTING_TIME);
                Log.logln(this.getAntId() + " Woke up.");
                controller.colony().exitRestingZone();
                Log.logln(this.getAntId() + " Left resting zone!");
                
                // Increase counter
                this.execCounter++;
                
            
            } catch(InterruptedException ie) {
            
                handleThreat();
                Thread.interrupted();
                
            } catch (IOException ex) {
                Logger.getLogger(SoldierAnt.class.getName()).log(Level.SEVERE, null, ex);
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
            // Checks all rooms within the colony and ensures to remove the ant from
            // them.
            controller.colony().exitEatingZone();
            controller.colony().exitInstructionZone();
            controller.colony().exitRestingZone();
        
            Log.logln(this.getAntId() + " WARNING: there is an invasion. Heading to the battlefield.");
        
            // Exits the colony
            controller.colony().exitColony();
            Log.logln(this.getAntId() + " Left the colony.");
            // Get cyclicbarrier from controller and await all other ants.
            Log.logln(this.getAntId() + " Waiting for my comrades!.");
            controller.threatBarrier().await();

            
            Thread.sleep(Config.THREAT_DURATION);
            
            
            controller.ant().getFightingList().remove(this);
            mutexThreatSem.acquire();
            if(controller.isUnderAttack()) {
            
                Log.logln(this.getAntId() + " Threat defeated!.");
                controller.stopAttack();
            
            }
            mutexThreatSem.release();
            Log.logln(this.getAntId() + " Back to routine.");
            
        } catch (InterruptedException ignored) {
            // EXCEPTION IGNORED
            // This block must NOT be reached.
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(SoldierAnt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoldierAnt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <b>EN</b>: Returns the {@code antId} formatted as per its type. <br><br>
     * <b>ES</b>: Devuelve el {@code antId} formateado según su tipo.
     * @return 
     *          String {@code antId} 
     */
    public String getAntId() {
        return "HS" + String.format("%04d", antId);
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
