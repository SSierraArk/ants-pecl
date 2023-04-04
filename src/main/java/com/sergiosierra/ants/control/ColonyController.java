/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.concurrent.Semaphore;
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
    Semaphore firstEntranceSem = new Semaphore(1, true);
    Semaphore secondEntranceSem = new Semaphore(1, true);
    
    Semaphore foodStorageSem =  new Semaphore(10, true);
    Lock foodMutexLock = new ReentrantLock();
    
    
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
    
    public void enterFoodStorage(int amount) {
   
        try {
            
            foodStorageSem.acquire();
            foodMutexLock.lock();
            colony.getFoodStorage().add((WorkerAnt) Thread.currentThread());
            colony.setFoodCount(colony.getFoodCount() + amount);

        } catch (InterruptedException ex) {
        } finally {
        
            foodMutexLock.unlock();
        
        }
        
    
    };
    
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
    
    public void exitFoodStorage(int amount) {
    
        try {
            
            foodMutexLock.lock();
            colony.getFoodStorage().remove((WorkerAnt) Thread.currentThread());
            colony.setFoodCount(colony.getFoodCount() - amount);
            foodStorageSem.release();
            
        } catch (Exception ie) {
        } finally {
            foodMutexLock.unlock();
        }
        
    
    };
    
}
