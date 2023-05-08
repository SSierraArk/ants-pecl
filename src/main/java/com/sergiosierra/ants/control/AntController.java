/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author ssierra
 */
public class AntController {
    
    
    private CopyOnWriteArrayList<SoldierAnt> soldierList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ChildAnt> childList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<WorkerAnt> workerList = new CopyOnWriteArrayList<>();
    
    private CopyOnWriteArrayList<WorkerAnt> lookingForFoodList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<SoldierAnt> fightingList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<WorkerAnt> carryingFoodList = new CopyOnWriteArrayList<>();
    
    public void setLookingForFood(WorkerAnt ant) {
    
        lookingForFoodList.add(ant);
    
    }
    
    public void unsetLookingForFood(WorkerAnt ant) {
    
        lookingForFoodList.remove(ant);
    
    }
    
    public void setCarryingFood(WorkerAnt ant) {
    
        carryingFoodList.add(ant);
    
    }
    
    public void unsetCarryingFood(WorkerAnt ant) {
    
        carryingFoodList.remove(ant);
    
    }

    public CopyOnWriteArrayList<SoldierAnt> getSoldierList() {
        return soldierList;
    }

    public void setSoldierList(CopyOnWriteArrayList<SoldierAnt> soldierList) {
        this.soldierList = soldierList;
    }

    public CopyOnWriteArrayList<ChildAnt> getChildList() {
        return childList;
    }

    public void setChildList(CopyOnWriteArrayList<ChildAnt> childList) {
        this.childList = childList;
    }

    public CopyOnWriteArrayList<WorkerAnt> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(CopyOnWriteArrayList<WorkerAnt> workerList) {
        this.workerList = workerList;
    }

    public CopyOnWriteArrayList<WorkerAnt> getLookingForFoodList() {
        return lookingForFoodList;
    }

    public void setLookingForFoodList(CopyOnWriteArrayList<WorkerAnt> lookingForFoodList) {
        this.lookingForFoodList = lookingForFoodList;
    }

    public CopyOnWriteArrayList<WorkerAnt> getCarryingFoodList() {
        return carryingFoodList;
    }

    public void setCarryingFoodList(CopyOnWriteArrayList<WorkerAnt> carryingFoodList) {
        this.carryingFoodList = carryingFoodList;
    }

    public CopyOnWriteArrayList<SoldierAnt> getFightingList() {
        return fightingList;
    }

    public void setFightingList(CopyOnWriteArrayList<SoldierAnt> fightingList) {
        this.fightingList = fightingList;
    }
    

    
    
    public int defend() {
    
        int counter = 0;
        for(SoldierAnt sa : soldierList) {
        
            sa.interrupt();
            fightingList.add(sa);
            counter++;
        
        }
        
        for(ChildAnt ca : childList) {
        
            ca.interrupt();
        
        }
        
        return counter;
        
    }
    
    
    
}
