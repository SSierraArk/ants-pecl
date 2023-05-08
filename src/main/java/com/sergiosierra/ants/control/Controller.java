/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.helpers.Response;
import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


/**
 *
 * @author ssierra
 */
public class Controller {
    

    private ColonyController colonyController;
    private AntController antController;
    private Semaphore pauseSem = new Semaphore(0, true);
    
    /**
     * This cyclic barrier will control all soldier ants outside of the colony once a
     * threat is detected.
     */
    private CyclicBarrier threatBarrier;
    
    /**
     * Semaphore that halts all child ants until the threat is gone.
     */
    private Semaphore threatSem =  new Semaphore(0, true);
    
    private boolean isPaused = false;
    private boolean underAttack = false;
    
    public Controller(Colony colony) {
        
        colonyController = new ColonyController(colony);
        antController = new AntController();
        
    }
    
    public void command(String request) {
    
        Response<String> response;
        
        response = new Response(400);
        response.addError("400: Bad request");
        
        if (request.equals("COMMAND//pause")) {
        
            pause();
            
        }
        
        if (request.equals("COMMAND//resume")) {
        
            resume();
        
        }
    
    }
    
    /**
     * This method will serve as an entry point for all requests made <br>
     * to the application.
     * @param request
     * @return 
     */
    public Response<String> fetch(String request) {
    
        Response<String> response;
        
        response = new Response(400);
        response.addError("400: Bad Request");
        
        if (request.equals("FETCH//server")) {
        
            HashMap<String, String> payload = new HashMap<>();
            
            // Ants looking for food.
            payload.put(
                "antsCollectingFoodText",
                ant().getLookingForFoodList().toString()
            );
            // Ants fighting
            payload.put(
                "soldiersFightingArea",
                "To be implemented"
            );
            // Ants in Storage room.
            payload.put(
                "antsInFoodStorageText",
                colony().getColony().getFoodStorage().toString()
            );
            // Ants carrying food.
            payload.put(
                "antsTransportingFoodText",
                ant().getCarryingFoodList().toString()
            );
            // Ants training.
            payload.put(
                "soldiersTrainingText",
                colony().getColony().getInstructionZone().toString()
            );
            // ants resting.
            payload.put(
                "antsRestingText",
                colony().getColony().getRestingZone().toString()
            );
            // Food count (Storage)
            payload.put(
                "foodInStorageText",
                Integer.toString(colony().getColony().getFoodCount())
            );
            // Food count (Eating zone)
            payload.put(
                "foodInEatingZoneText",
                Integer.toString(colony().getColony().getEatingZoneFoodCount())
            );
            // Ants in Eating Zone
            payload.put(
                "eatingZoneArea",
                colony().getColony().getEatingZone().toString()
            );
            // Ants within shelter
            payload.put(
                "shelterArea",
                colony().getColony().getShelter().toString()
            );
            
            response = new Response(200);
            response.setPayload(payload);
            
        
        }
        
        if (request.equals("GET//client")) {
        
            
        
        }
        
    
        return response;
    
    }
    
    // Factory methods
    
    public WorkerAnt spawnWorkerAnt() {
    
        WorkerAnt ant = new WorkerAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;
        
    }
    
    public SoldierAnt spawnSoldierAnt() {
    
        SoldierAnt ant = new SoldierAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;    
    }
    
    public ChildAnt spawnChildAnt() {
    
        ChildAnt ant = new ChildAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;    
    }
    
    /**
     * This methods returns the {@code ColonyController} object linked to this
     * {@code Controller} instance.
     * @return ColonyController
     */
    public ColonyController colony() {
    
        return colonyController;
    
    }
    
    
    public AntController ant() {
    
        return antController;
        
    }
    
    public Semaphore pauseSem() {
    
        return pauseSem;
        
    }
    
    public Semaphore threatSem() {
    
        return threatSem;
        
    }
    
    public CyclicBarrier threatBarrier() {
    
        return threatBarrier;
    
    }
    
    public boolean isUnderAttack() {
    
        return underAttack;
    
    }
    
    public void startAttack() {
    
        this.underAttack = true;
        
        this.threatBarrier = new CyclicBarrier(ant().defend());
        
    
    }
    
    /**
     * This method stops the attack (sets the underAttack flag to false) and <br>
     * releases all ants waiting for the threatSem Semaphore
     */
    public void stopAttack() {
    
        this.underAttack = false;
        this.threatSem.release(
            this.threatSem.getQueueLength());
        
    }
    
    public boolean isPaused() {
    
        return isPaused;
    
    }
    
    /**
     * EN: This method will modify the controller "isPaused" flag to true. All
     * objects implementing the Pausable interface will pause.
     * ES: Este método modificará el valor del atributo "isPaused" a true. Todos
     * los objetos implementando la interfaz Pausable se pausarán.
     */
    public void pause() {
    
        this.isPaused = true;
    
    }
    
    /**
     * EN: Resumes all paused threads and sets the isPaused flag to false.
     * ES: Reanuda la ejecución de todos los hilos pausados y resetea el valor
     * del flag "isPaused" a false.
     */
    public void resume() {
    
        pauseSem.release(pauseSem.getQueueLength());
        this.isPaused = false;
    
    }
    
}
