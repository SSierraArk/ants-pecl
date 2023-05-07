/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.exceptions.ColonyAccessException;
import com.sergiosierra.ants.models.Ant;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;

/**
 * This class takes a Colony type object and provides THREAD-SAFE methods <br>
 * for threads to operate with it comfortably and safely.
 * @author ssierra
 */
public class ColonyController {
    
    Colony colony; // Colony model object.
    
    // Sync + comm mechanisms.
    
    Semaphore exitSem = new Semaphore(2, true);
    // enterSem has two permits as there are two entrance gates.
    Semaphore enterSem = new Semaphore(1, true); 
    
    Semaphore foodStorageSem =  new Semaphore(10, true);
    Lock foodMutexLock = new ReentrantLock();
    Condition noFood = foodMutexLock.newCondition();
    
    /**
     *
     * @param colony
     */
    public ColonyController(Colony colony) {
    
        this.colony = colony;
        
    }

    /**
     * Returns a Colony type object linked to this ColonyController.
     * @return Colony
     */
    public Colony getColony() {
        return colony;
    }
    
    /**
     * Enters the food storage room.<br>
     * (THREAD-SAFE METHOD)
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
     * This method takes an {@code int}, enters the food storage room and drops.<br>
     * as many items as inputted as a parameter.
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
     *
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
     *
     * @param amount
     * @throws java.lang.InterruptedException
     */
    public void exitFoodStorage(int amount) throws InterruptedException {
    
        try {
            
            foodMutexLock.lock();
            while(colony.getFoodCount() < amount) {
                noFood.await();
            }
            colony.setFoodCount(colony.getFoodCount() - amount);
            colony.getFoodStorage().remove((WorkerAnt) Thread.currentThread());
            foodStorageSem.release();
            
        } finally {
            foodMutexLock.unlock();
        }
        
    
    };
    
    /**
     * The thread executing this method will enter the colony.If the thread is already <br>
     * within the colony, no action will be taken. (THREAD-SAFE METHOD). <br><br>
     * 
     * Access to the colony is controlled by a fair Semaphore object.
     * @throws java.lang.InterruptedException
     */
    public void enterColony() throws InterruptedException {
    
        try {
            enterSem.acquire();
            //Logger.println("Enter Semaphore acquired -> " + Thread.currentThread().getName() + " permits: " + enterSem.availablePermits(), Boolean.TRUE);
            Thread.sleep(100);
            //Logger.println("Woke up!" + Thread.currentThread().getName(), Boolean.TRUE);
            if(!colony.getInside().contains((Ant) Thread.currentThread())) colony.getInside().add((Ant) Thread.currentThread());
            if(colony.getOutside().contains((Ant) Thread.currentThread())) colony.getOutside().remove((Ant) Thread.currentThread());
            //Logger.println("Enter Semaphore released -> " + Thread.currentThread().getName(), Boolean.TRUE);

            
        } finally {
        
            
            enterSem.release();

        }
        
    }
    
    /**
     * The thread executing this method will exit the colony (if actually inside it), otherwise <br>
     * no action will be taken.(THREAD-SAFE METHOD).<br><br>
     * 
     * Access to the outside is controlled by a fair Semaphore object.
     * @throws java.lang.InterruptedException
     */
    public void exitColony() throws InterruptedException {
    
        try {

            exitSem.acquire();
            //Logger.println("Semaphore acquired -> " + Thread.currentThread().getName() + " permits: " + enterSem.availablePermits(), Boolean.TRUE);
            Thread.sleep(100);
            if(!colony.getOutside().contains((Ant) Thread.currentThread())) colony.getOutside().add((Ant) Thread.currentThread());
            if(colony.getInside().contains((Ant) Thread.currentThread())) colony.getInside().remove((Ant) Thread.currentThread());
            //Logger.println("Semaphore released -> " + Thread.currentThread().getName(), Boolean.TRUE);
        
        } finally {
            
            exitSem.release();
        
        }
    
    }
    
    
    public void enterShelter() {
    
        if(colony.getInside().contains((Ant) Thread.currentThread())) {
        
            colony.getShelter().add((ChildAnt) Thread.currentThread());

        }
            
    
    }
    
    public void exitShelter() {
    
        if(colony.getShelter().contains((ChildAnt) Thread.currentThread())) {
        
            colony.getShelter().remove((ChildAnt) Thread.currentThread());
        
        }
    
    }
    
    public void enterEatingZone() throws ColonyAccessException {
    
        if(colony.getInside().contains((Ant) Thread.currentThread())) {
        
            colony.getEatingZone().add((Ant) Thread.currentThread());
            
        } else throw new ColonyAccessException(
            "Could not enter eating zone, this ant is either outside the colony"
            + "or already within some other zone inside the colony."
        );
    
    }
    
    
    /**
     * The thread executing this method will exit the eating zone (if actually inside it), otherwise <br>
     * no action will be taken. (THREAD-SAFE METHOD). <br><br>
     * 
     */
    public void exitEatingZone() {
    
        if(colony.getEatingZone().contains((Ant) Thread.currentThread())) {
        
            colony.getEatingZone().remove((Ant) Thread.currentThread());
        
        }
    
    }
    
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
     * This method takes an {@code int}, enters the eating zone and drops <br>
     * as many items as inputted as a parameter.
     * @param amount
     *        Number of items to be dropped.
     * @throws ColonyAccessException
     */
    public synchronized void enterEatingZone(int amount) throws ColonyAccessException {
    
        enterEatingZone();
        // Add amount of food to the eating zone.
        colony.setEatingZoneFoodCount(
            colony.getEatingZoneFoodCount() + amount
        );
        notifyAll();
    
    }
    
    public void enterRestingZone() {
    
        if(colony.getInside().contains((Ant) Thread.currentThread())) {
        
            colony.getRestingZone().add((Ant) Thread.currentThread());
        
        }
    
    }
    
    public void exitRestingZone() {
    
        if(colony.getRestingZone().contains((Ant) Thread.currentThread())) {
        
            colony.getRestingZone().remove((Ant) Thread.currentThread());
        
        }
    
    }
    
    public void enterInstructionZone() {
    
        if(colony.getInside().contains((SoldierAnt) Thread.currentThread())) {
        
            colony.getInstructionZone().add((SoldierAnt) Thread.currentThread());
        
        }
    
    }
    
    public void exitInstructionZone() {
    
        if(colony.getInstructionZone().contains((SoldierAnt) Thread.currentThread())) {
        
            colony.getInstructionZone().remove((Ant) Thread.currentThread());
        
        }
    
    }
    
    
}
