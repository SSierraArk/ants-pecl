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

/**
 *
 * @author ssierra
 */
public class ColonyController {
    
    Colony colony; // Colony model object.
    
    // Sync + comm mechanisms.
    
    Semaphore exitSem = new Semaphore(1, true);
    // enterSem has two permits as there are two entrance gates.
    Semaphore enterSem = new Semaphore(2, true); 
    
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
     *
     * @return
     */
    public Colony getColony() {
        return colony;
    }
    
    /**
     *
     */
    public void enterFoodStorage() {
   
        try {
            
            foodStorageSem.acquire();
            foodMutexLock.lock();
            colony.getFoodStorage().add((WorkerAnt) Thread.currentThread());
            
        } catch (InterruptedException ex) {
        } finally {
        
            foodMutexLock.unlock();
        
        }
        
    
    };
    
    /**
     *
     * @param amount
     */
    public void enterFoodStorage(int amount) {
   
        try {
            
            foodStorageSem.acquire();
            foodMutexLock.lock();
            colony.getFoodStorage().add((WorkerAnt) Thread.currentThread());
            colony.setFoodCount(colony.getFoodCount() + amount);
            noFood.signalAll();

        } catch (InterruptedException ex) {
        } finally {
            foodMutexLock.unlock();
        
        }
        
    
    };
    
    /**
     *
     */
    public void exitFoodStorage() {
        
        try {
            
            foodMutexLock.lock();
            colony.getFoodStorage().remove((WorkerAnt) Thread.currentThread());
            foodStorageSem.release();
            
        } catch (Exception e) {
        } finally {
            foodMutexLock.unlock();
        }
        
    
    };
    
    /**
     *
     * @param amount
     */
    public void exitFoodStorage(int amount) {
    
        try {
            
            foodMutexLock.lock();
            while(colony.getFoodCount() < amount) {
                noFood.await();
            }
            colony.setFoodCount(colony.getFoodCount() - amount);
            colony.getFoodStorage().remove((WorkerAnt) Thread.currentThread());
            foodStorageSem.release();
            
        } catch (Exception ie) {
        } finally {
            foodMutexLock.unlock();
        }
        
    
    };
    
    public void enterColony() {
    
        
    
    }
    
    public void exitColony() {
    
        try {

            exitSem.acquire();
            Thread.sleep(100);
            if(!colony.getOutside().contains((Ant) Thread.currentThread())) colony.getOutside().add((Ant) Thread.currentThread());
            if(colony.getInside().contains((Ant) Thread.currentThread())) colony.getInside().remove((Ant) Thread.currentThread());

            

        } catch (InterruptedException ie) {
        
        } finally {
            
            exitSem.release();
        
        }
        
    
    }
    
}
