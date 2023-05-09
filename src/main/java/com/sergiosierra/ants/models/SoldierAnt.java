/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
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

            
            Thread.sleep(20000);
            
            // Threat defeated!
            controller.ant().getFightingList().remove(this);
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
