/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.helpers.Log;
import com.sergiosierra.ants.helpers.Response;
import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>EN</b>: This class provides control logic for the whole application. All other <br>
 * model controllers can be both instantiated and retrieved from this. It also provides <br>
 * some ready-to-use factory methods and some thread control mechanisms. <br><br>
 * <b>ES</b>: Esta clase proporciona la lógica de control para la aplicación al completo. <br>
 * Todos los otros controladores (ligados a modelos) pueden ser tanto instanciados como accedidos <br>
 * desde esta misma. Además se incluyen algunos métodos de factoría listos para usar y mecanismos de <br>
 * control de hilos.
 * @author ssierra
 */
public class Controller {
    
    /**
     * <b>EN</b>: {@code ColonyController} linked to this main controller instance. <br><br>
     * <b>ES</b>: {@code ColonyController} ligado a esta instancia de controlador principal.
     */
    private ColonyController colonyController;
    
    /**
     * <b>EN</b>: {@code AntController} linked to this main controller instance. <br><br>
     * <b>ES</b>: {@code AntController} ligado a esta instancia de controlador principal.
     */
    private AntController antController;
    
    /**
     * <b>EN</b>: Semaphore that halts all {@code Pausable} instances until the the application is resumed. <br><br>
     * <b>ES</b>: Semáforo que frena a todas las instancias {@code Pausable} hasta que la aplicación se reanude.
     */
    private Semaphore pauseSem = new Semaphore(0, true);
    
    /**
     * <b>EN</b>: This cyclic barrier will control all soldier ants outside of the colony once a <br>
     * threat is detected. <br><br>
     * <b>ES</b>: Esta barrera cíclica controlará a todas las hormigas soldado que se encuentren fuera <br>
     * de la colonia una vez se haya detectado una amenaza.
     */
    private CyclicBarrier threatBarrier;
    
    /**
     * <b>EN</b>: Semaphore that halts all child ants until the threat is gone. <br><br>
     * <b>ES</b>: Semáforo que frena a todas las hormigas cría hasta que la amenaza desaparece.
     */
    private Semaphore threatSem =  new Semaphore(0, true);
    
    /**
     * <b>EN</b>: Status flag determining whether the application is paused or not. <br><br>
     * <b>ES</b>: Bandera de estado que determina si la aplicación está o no en pausa.
     */
    private boolean isPaused = false;
    
    /**
     * <b>EN</b>: Status flag determining whether the colony is under attack or not. <br><br>
     * <b>ES</b>: Bandera de estado que determina si la colonia está o no en espera.
     */
    private boolean underAttack = false;
    
    
    public Controller(Colony colony) {
        
        colonyController = new ColonyController(colony);
        antController = new AntController();
        
    }
    
    /**
     * <b>EN</b>: This method will serve as an entry point for all requests made <br>
     * to the application. <br><br>
     * <b>ES</b>: Este método servirá como punto de entrada para todas las peticiones que
     * la aplicación reciba.
     * @param request
     * @return 
     */
    public Response<String> query(String request) {
    
        Response<String> response;
        
        response = new Response(400);
        response.addError("400: Bad Request");
        
        if (request.equals("COMMAND//pause")) {
        
            pause();
            response.setStatus(200);
            response.flushErrorBag();
            
        }
        
        if (request.equals("COMMAND//resume")) {
        
            resume();
            response.setStatus(200);
            response.flushErrorBag();
        }
        
        if (request.equals("COMMAND//attack")) {
        
            // Only allows to start the attack if the colony was not previously
            // being attacked.
            startAttack();
            response.setStatus(200);
            response.flushErrorBag();
        }
        
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
                ant().getFightingList().toString()
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
        
        if (request.equals("FETCH//client")) {
        
            HashMap<String, String> payload = new HashMap<>();
            int childAntsEating = (int) colony().getColony().getEatingZone().
                    stream().filter(elem -> elem instanceof ChildAnt).count();
            
            
            // Ants inside the colony.
            payload.put(
                "antsInsideText",
                Integer.toString(colony().getColony().getInside().size())
            );
            // Ants outside the colony
            payload.put(
                "antsOutsideText",
                Integer.toString(colony().getColony().getOutside().size())
            );
            // Child ants eating.
            payload.put(
                "childAntsEatingText",
                Integer.toString(childAntsEating)
            );
            // Ants hiding.
            payload.put(
                "childAntsHidingText",
                Integer.toString(colony().getColony().getShelter().size())
            );
            // Ants training.
            payload.put(
                "soldiersTrainingText",
                Integer.toString(colony().getColony().getInstructionZone().size())
            );
            // ants fighting.
            payload.put(
                "soldiersFightingText",
                Integer.toString(ant().getFightingList().size())
            );
            
            response = new Response(200);
            response.setPayload(payload);
            
        
        }
        
        return response;
    
    }
    
    
    // Factory methods
    
    /**
     * <b>EN</b>: Factory method for {@code WorkerAnt}. It automatically includes <br>
     * the instance within the {@code AntController} respective data structure.<br><br>
     * <b>ES</b>: Método de factoría para {@code WorkerAnt}. También incluye esta <br>
     * instancia en de la estructura correspondiente dentro de {@code AntController}.
     * @return
     *      {@code WorkerAnt ant}
     */
    public WorkerAnt spawnWorkerAnt() {
    
        WorkerAnt ant = new WorkerAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;
        
    }
    
    /**
     * <b>EN</b>: Factory method for {@code SoldierAnt}. It automatically includes <br>
     * the instance within the {@code AntController} respective data structure.<br><br>
     * <b>ES</b>: Método de factoría para {@code SoldierAnt}. También incluye esta <br>
     * instancia en de la estructura correspondiente dentro de {@code AntController}.
     * @return
     *      {@code SoldierAnt ant}
     */
    public SoldierAnt spawnSoldierAnt() {
    
        SoldierAnt ant = new SoldierAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;    
    }
    
    /**
     * <b>EN</b>: Factory method for {@code ChildAnt}. It automatically includes <br>
     * the instance within the {@code AntController} respective data structure.<br><br>
     * <b>ES</b>: Método de factoría para {@code ChildAnt}. También incluye esta <br>
     * instancia en de la estructura correspondiente dentro de {@code AntController}.
     * @return
     *      {@code ChildAnt ant}
     */
    public ChildAnt spawnChildAnt() {
    
        ChildAnt ant = new ChildAnt(this);
        this.colonyController.colony.getOutside().add(ant);
        return ant;    
    }
    
    /**
     * <b>EN</b>: This methods returns the {@code ColonyController} object linked to this
     * {@code Controller} instance. <br><br>
     * <b>ES</b>: Este método devuelve el {@code ColonyController} ligado a esta instancia de <br>
     * {@code Controller}.
     * @return ColonyController colonyController
     */
    public ColonyController colony() {
    
        return colonyController;
    
    }
    
    /**
     * <b>EN</b>: This methods returns the {@code AntController} object linked to this
     * {@code Controller} instance. <br><br>
     * <b>ES</b>: Este método devuelve el {@code AntController} ligado a esta instancia de <br>
     * {@code Controller}.
     * @return AntController antController
     */
    public AntController ant() {
    
        return antController;
        
    }
    
    /**
     * <b>EN</b>: Getter for {@link Controller#pauseSem}. <br><br>
     * <b>ES</b>: Getter de {@link Controller#pauseSem}.
     * @return Semaphore pauseSem
     */
    public Semaphore pauseSem() {
    
        return pauseSem;
        
    }
    
    /**
     * <b>EN</b>: Getter for {@link Controller#threatSem}. <br><br>
     * <b>ES</b>: Getter de {@link Controller#threatSem}.
     * @return Semaphore threatSem
     */
    public Semaphore threatSem() {
    
        return threatSem;
        
    }
    
    /**
     * <b>EN</b>: Getter for {@link Controller#threatBarrier}. <br><br>
     * <b>ES</b>: Getter de {@link Controller#threatBarrier}.
     * @return CyclicBarrier threatBarrier
     */
    public CyclicBarrier threatBarrier() {
    
        return threatBarrier;
    
    }
    
    /**
     * <b>EN</b>: Getter for {@link Controller#underAttack} flag. <br><br>
     * <b>ES</b>: Getter de {@link Controller#underAttack} bandera de estado.
     * @return boolean underAttack
     */
    public boolean isUnderAttack() {
    
        return underAttack;
    
    }
    
    /**
     * <b>EN</b>: This method starts the attack (sets the underAttack flag to true) ONLY if <br>
     * no attack is already taking place. <br><br>
     * <b>ES</b>: Este método comienza el ataque en marcha (fija la bandera de estado underAttack a true) <br>
     * SOLO si no había ningún ataque previamente en marcha.
     */
    public void startAttack() {
    
        if (!underAttack && !ant().getSoldierList().isEmpty()) {
        
            this.underAttack = true;
        
            this.threatBarrier = new CyclicBarrier(ant().defend());
        
        }
        
        
    
    }
    
    /**
     * <b>EN</b>: This method stops the attack (sets the underAttack flag to false) and <br>
     * releases all ants waiting for the threatSem Semaphore. <br><br>
     * <b>ES</b>: Este método frena el ataque en marcha (fija la bandera de estado underAttack a false) y <br>
     * libera a todas las hormigas que estuvieran esperando por el semáforo threatSem.
     */
    public void stopAttack() {
    
        this.underAttack = false;
        this.threatSem.release(
            this.threatSem.getQueueLength());
        
    }
    
    /**
     * <b>EN</b>: Getter for {@link Controller#isPaused} flag. <br><br>
     * <b>ES</b>: Getter de {@link Controller#isPaused} bandera de estado.
     * @return boolean isPaused
     */
    public boolean isPaused() {
    
        return isPaused;
    
    }
    
    /**
     * <b>EN</b>: This method will modify the controller "isPaused" flag to true. All <br>
     * objects implementing the Pausable interface will pause. <br><br>
     * <b>ES</b>: Este método modificará el valor del atributo "isPaused" a true. Todos <br>
     * los objetos implementando la interfaz Pausable se pausarán.
     */
    public void pause() {
    
        this.isPaused = true;
        try {
            Log.logln("SYSTEM: Pause signal sent!");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    /**
     * <b>EN</b>: Resumes all paused threads and sets the isPaused flag to false. <br><br>
     * <b>ES</b>: Reanuda la ejecución de todos los hilos pausados y resetea el valor <br>
     * del flag "isPaused" a false.
     */
    public void resume() {
    
        pauseSem.release(pauseSem.getQueueLength());
        this.isPaused = false;
        try {
            Log.logln("SYSTEM: Resume signal sent!");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
