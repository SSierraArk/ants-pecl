/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.Ant;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.util.Config;
import java.util.concurrent.TimeUnit;

/**
 * <b>EN</b>: This class takes a Colony type object and provides THREAD-SAFE methods <br>
 * for threads to operate with it comfortably and safely.<br><br>
 * <b>ES</b>: Esta clase toma un objeto Colony y proporciona métodos THREAD-SAFE para que <br>
 * los hilos puedan operar con esta misma de manera cómoda y segura.
 * @author ssierra
 */
public class ColonyController {
    
    /**
     * <b>EN</b>: Colony model object. <br><br>
     * <b>ES</b>: Objeto modelo de colonia (Colony).
     */
    Colony colony;
    
    // Sync + comm mechanisms.
    
    /**
     * <b>EN</b>: Semaphore controls the colony exit. <br><br>
     * <b>ES</b>: Semáforo que controla la salida de la colonia.
     */
    Semaphore exitSem = new Semaphore(Config.COL_EXITS, true);
    
    /**
     * <b>EN</b>: Semaphore controls the colony entrance. <br><br>
     * <b>ES</b>: Semáforo que controla la entrada de la colonia.
     */
    Semaphore enterSem = new Semaphore(Config.COL_ENTRANCES, true); 
    
    /**
     * <b>EN</b>: Semaphore controls the food storage room's maximum capacity. <br><br>
     * <b>ES</b>: Semáforo que controla el aforo máximo del almacén de la colonia.
     */
    Semaphore foodStorageSem =  new Semaphore(Config.COL_FOODSTORAGE, true);
    
    /**
     * <b>EN</b>: Lock ensuring mutual exclusion for the food storage food stack. <br><br>
     * <b>ES</b>: Cerrojo que asegura exclusión mutua a la pila de comida del almacén.
     */
    Lock foodMutexLock = new ReentrantLock();
    
    /**
     * <b>EN</b>: Condition that makes threads wait upon some food is available. <br><br>
     * <b>ES</b>: Condición que hará que los hilos esperen hasta que haya comida disponible.
     */
    Condition noFood = foodMutexLock.newCondition();
    
    /**
     * Default constructor.
     * @param colony
     */
    public ColonyController(Colony colony) {
    
        this.colony = colony;
        
    }

    /**
     * {@link ColonyController#colony}
     * @return 
     *      {@code Colony colony}
     */
    public Colony getColony() {
        return colony;
    }
    
    /**
     * <b>EN</b>: Enters the food storage room. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede al almacén de comida. (Método THREAD-SAFE).
     * @throws java.lang.InterruptedException
     */
    public void enterFoodStorage() throws InterruptedException {
   
        try {
            
            foodStorageSem.acquire();
            foodMutexLock.lock();
            colony.getFoodStorage().add((WorkerAnt) Thread.currentThread());
            
        } finally {
        
            foodMutexLock.unlock();
        
        }
        
    
    };
    
    /**
     * <b>EN</b>: Enters the food storage room dropping {@code amount} food items. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede al almacén de comida dejando {@code amount} elementos de comida. (Método THREAD-SAFE).
     * @param amount
     *        Number of items to be dropped.
     * @throws java.lang.InterruptedException
     */
    public void enterFoodStorage(int amount) throws InterruptedException {
   
        try {
            
            foodStorageSem.acquire();
            foodMutexLock.lock();
            colony.getFoodStorage().add((WorkerAnt) Thread.currentThread());
            colony.setFoodCount(colony.getFoodCount() + amount);
            noFood.signalAll();

        } finally {
            foodMutexLock.unlock();
        
        }
        
    
    };
    
    /**
     * <b>EN</b>: Exits the food storage room. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Sale del almacén de comida. (Método THREAD-SAFE).
     * @throws java.lang.InterruptedException
     */
    public void exitFoodStorage() throws InterruptedException {
        
        try {
            
            foodMutexLock.lock();
            colony.getFoodStorage().remove((WorkerAnt) Thread.currentThread());
            foodStorageSem.release();
            
        } finally {
            
            foodMutexLock.unlock();
        
        }
        
    
    };
    
    /**
     * <b>EN</b>: Exits the food storage room taking {@code amount} food elements if available. <br>
     * In case there are not enough food elements, the thread will wait {@code timeoutCount} iterations. <br>
     * and, if there is still no food available, it will leave taking no food elements. (THREAD-SAFE METHOD).<br><br>
     * <b>ES</b>: Sale del almacén de comida llevándose {@code amount} elementos de comida si están disponible. <br>
     * En caso de no estar disponibles, esperará {@code timeoutCount} iteraciones y, si todavía no hay, se marchará sin nada. (Método THREAD-SAFE).
     * @param amount
     * @param timeoutCount
     * @return 
     * @throws java.lang.InterruptedException
     */
    public boolean exitFoodStorage(int amount, int timeoutCount) throws InterruptedException {
    
        int counter = 0;
        try {
            
            foodMutexLock.lock();
            while(colony.getFoodCount() < amount && counter < timeoutCount) {
                noFood.await(Config.COL_FOODSTORAGE_TIMEOUT, TimeUnit.SECONDS);
                counter++;
            }
            
            if (counter < timeoutCount) colony.setFoodCount(colony.getFoodCount() - amount);
            colony.getFoodStorage().remove((WorkerAnt) Thread.currentThread());
            
        } finally {
            foodMutexLock.unlock();
            foodStorageSem.release();
            
        }
        return counter == timeoutCount;
    
    };
    
    /**
     * <b>EN</b>: Enters the colony if not already in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede a la colonia si no estaba dentro. (Método THREAD-SAFE).
     * @throws java.lang.InterruptedException
     */
    public void enterColony() throws InterruptedException {
    
        try {
            enterSem.acquire();
            Thread.sleep(Config.COL_ENTRANCE_DELAY);
            if(!colony.getInside().contains((Ant) Thread.currentThread())) colony.getInside().add((Ant) Thread.currentThread());
            if(colony.getOutside().contains((Ant) Thread.currentThread())) colony.getOutside().remove((Ant) Thread.currentThread());

            
        } finally {
        
            
            enterSem.release();

        }
        
    }
    
    /**
     * <b>EN</b>: Exits the colony if previously inside. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Sale de la colonia si no estaba dentro. (Método THREAD-SAFE).
     * @throws java.lang.InterruptedException
     */
    public void exitColony() throws InterruptedException {
    
        try {
            if(!colony.getOutside().contains((Ant) Thread.currentThread()) && colony.getInside().contains((Ant) Thread.currentThread())) {
            
            
            exitSem.acquire();
            //Logger.println("Semaphore acquired -> " + Thread.currentThread().getName() + " permits: " + enterSem.availablePermits(), Boolean.TRUE);
            Thread.sleep(Config.COL_ENTRANCE_DELAY);
            colony.getOutside().add((Ant) Thread.currentThread());
            colony.getInside().remove((Ant) Thread.currentThread());
            //Logger.println("Semaphore released -> " + Thread.currentThread().getName(), Boolean.TRUE);
        
            }
            
        } finally {
            
            exitSem.release();
        
        }
    
    }
    
    /**
     * <b>EN</b>: Enters the shelter if not already in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede al refugio si no estaba dentro. (Método THREAD-SAFE).
     */
    public void enterShelter() {
    
        if(colony.getInside().contains((Ant) Thread.currentThread())) {
        
            colony.getShelter().add((ChildAnt) Thread.currentThread());

        }
            
    
    }
    
    /**
     * <b>EN</b>: Exits the shelter if previously in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Sale del refugio si estaba dentro. (Método THREAD-SAFE).
     */
    public void exitShelter() {
    
        if(colony.getShelter().contains((ChildAnt) Thread.currentThread())) {
        
            colony.getShelter().remove((ChildAnt) Thread.currentThread());
        
        }
    
    }
    
    /**
     * <b>EN</b>: Enters the eating zone if not already in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede al comedor si no estaba dentro. (Método THREAD-SAFE).
     */
    public void enterEatingZone() {
    
        if(colony.getInside().contains((Ant) Thread.currentThread())) {
        
            colony.getEatingZone().add((Ant) Thread.currentThread());
            
        }
    
    }
    
    
    /**
     * <b>EN</b>: Exits the eating zone if previously in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Sale del comedor si estaba dentro. (Método THREAD-SAFE).
     */
    public void exitEatingZone() {
    
        if(colony.getEatingZone().contains((Ant) Thread.currentThread())) {
        
            colony.getEatingZone().remove((Ant) Thread.currentThread());
        
        }
    
    }
    
    /**
     * <b>EN</b>: Eats a certain {@code amount} of food if available, if not, the thread will <br>
     * wait until there is enough food. (THREAD-SAFE METHOD) (Monitor - {@link ColonyController#enterEatingZone(int)}).<br><br>
     * <b>ES</b>: Come una cierta {@code amount} de comida si está disponible, si no, el hilo <br>
     * esperará a que haya suficiente comida.(Método THREAD-SAFE) (Monitor - {@link ColonyController#enterEatingZone(int)}).
     * @param amount
     * @throws java.lang.InterruptedException
     */
    public synchronized void eat(int amount) throws InterruptedException {
        
        // Check whether there is enough food available at the
        // eating zone.
        while(colony.getEatingZoneFoodCount() < amount) {
            wait();

        }
    
        colony.setEatingZoneFoodCount(
            colony.getEatingZoneFoodCount() - amount
        );
    
    }
    
    
    /**
     * <b>EN</b>: Drops a certain {@code amount} of food.(THREAD-SAFE METHOD) (Monitor - {@link ColonyController#eat(int)}).<br><br>
     * <b>ES</b>: Deja una cierta {@code amount} de comida.(Método THREAD-SAFE) (Monitor - {@link ColonyController#eat(int)}).
     * @param amount
     */
    public synchronized void enterEatingZone(int amount) {
    
        enterEatingZone();
        // Add amount of food to the eating zone.
        colony.setEatingZoneFoodCount(
            colony.getEatingZoneFoodCount() + amount
        );
        notifyAll();
    
    }
    
    
    /**
     * <b>EN</b>: Enters the resting zone if not already in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede a la zona de descanso si no estaba dentro. (Método THREAD-SAFE).
     */
    public void enterRestingZone() {
    
        if(colony.getInside().contains((Ant) Thread.currentThread())) {
        
            colony.getRestingZone().add((Ant) Thread.currentThread());
        
        }
    
    }
    
    /**
     * <b>EN</b>: Exits the resting zone if previously in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Sale de la zona de descanso si estaba dentro. (Método THREAD-SAFE).
     */
    public void exitRestingZone() {
    
        if(colony.getRestingZone().contains((Ant) Thread.currentThread())) {
        
            colony.getRestingZone().remove((Ant) Thread.currentThread());
        
        }
    
    }
    
    /**
     * <b>EN</b>: Enters the instruction zone if not already in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Accede a la zona de entrenamiento si no estaba dentro. (Método THREAD-SAFE).
     */
    public void enterInstructionZone() {
    
        if(colony.getInside().contains((SoldierAnt) Thread.currentThread())) {
        
            colony.getInstructionZone().add((SoldierAnt) Thread.currentThread());
        
        }
    
    }
    
    /**
     * <b>EN</b>: Exits the instruction zone if previously in. (THREAD-SAFE METHOD). <br><br>
     * <b>ES</b>: Sale de la zona de entrenamiento si estaba dentro. (Método THREAD-SAFE).
     */
    public void exitInstructionZone() {
    
        if(colony.getInstructionZone().contains((SoldierAnt) Thread.currentThread())) {
        
            colony.getInstructionZone().remove((Ant) Thread.currentThread());
        
        }
    
    }
    
    
}
