/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <b>EN</b>: This class provides ant-specific control logic. <br><br>
 * <b>ES</b>: Esta clase contiene lógica de control relacionada con las hormigas.
 * @author ssierra
 */
public class AntController {
    
    /**
     * <b>EN</b>: {@code CopyOnWriteArrayList} containing all {@code SoldierAnt} instances. <br><br>
     * <b>ES</b>: {@code CopyOnWriteArrayList} que contiene todas las instancias de {@code SoldierAnt}.
     */
    private CopyOnWriteArrayList<SoldierAnt> soldierList = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: {@code CopyOnWriteArrayList} containing all {@code ChildAnt} instances. <br><br>
     * <b>ES</b>: {@code CopyOnWriteArrayList} que contiene todas las instancias de {@code ChildAnt}.
     */
    private CopyOnWriteArrayList<ChildAnt> childList = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: {@code CopyOnWriteArrayList} containing all {@code WorkerAnt} instances. <br><br>
     * <b>ES</b>: {@code CopyOnWriteArrayList} que contiene todas las instancias de {@code WorkerAnt}.
     */
    private CopyOnWriteArrayList<WorkerAnt> workerList = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: {@code CopyOnWriteArrayList} containing all {@code WorkerAnt} instances looking for food. <br><br>
     * <b>ES</b>: {@code CopyOnWriteArrayList} que contiene todas las instancias de {@code WorkerAnt} que buscan comida.
     */
    private CopyOnWriteArrayList<WorkerAnt> lookingForFoodList = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: {@code CopyOnWriteArrayList} containing all {@code SoldierAnt} instances that are fighting. <br><br>
     * <b>ES</b>: {@code CopyOnWriteArrayList} que contiene todas las instancias de {@code SoldierAnt} que se encuentran luchando.
     */
    private CopyOnWriteArrayList<SoldierAnt> fightingList = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: {@code CopyOnWriteArrayList} containing all {@code WorkerAnt} instances carrying for food. <br><br>
     * <b>ES</b>: {@code CopyOnWriteArrayList} que contiene todas las instancias de {@code WorkerAnt} que están llevando comida.
     */
    private CopyOnWriteArrayList<WorkerAnt> carryingFoodList = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: Adds a given {@code WorkerAnt} to the list of instances looking for food.<br><br>
     * <b>ES</b>: Añade una {@code WorkerAnt} a la lista de instancias que buscan comida.
     * @param ant {@code WorkerAnt} instance
     */
    public void setLookingForFood(WorkerAnt ant) {
    
        lookingForFoodList.add(ant);
    
    }
    
    /**
     * <b>EN</b>: Removes a given {@code WorkerAnt} from the list of instances looking for food.<br><br>
     * <b>ES</b>: Elimina una {@code WorkerAnt} de la lista de instancias que buscan comida.
     * @param ant {@code WorkerAnt} instance
     */
    public void unsetLookingForFood(WorkerAnt ant) {
    
        lookingForFoodList.remove(ant);
    
    }
    
    /**
     * <b>EN</b>: Adds a given {@code WorkerAnt} to the list of instances carrying food.<br><br>
     * <b>ES</b>: Añade una {@code WorkerAnt} a la lista de instancias que transportan comida.
     * @param ant {@code WorkerAnt} instance
     */
    public void setCarryingFood(WorkerAnt ant) {
    
        carryingFoodList.add(ant);
    
    }
    
    /**
     * <b>EN</b>: Removes a given {@code WorkerAnt} from the list of instances carrying for food.<br><br>
     * <b>ES</b>: Elimina una {@code WorkerAnt} de la lista de instancias que transportan comida.
     * @param ant {@code WorkerAnt} instance
     */
    public void unsetCarryingFood(WorkerAnt ant) {
    
        carryingFoodList.remove(ant);
    
    }

    
    // Getters and setters.
    
    /**
     * {@link AntController#soldierList}
     * @return 
     *      {@code soldierList}
     */
    public CopyOnWriteArrayList<SoldierAnt> getSoldierList() {
        return soldierList;
    }

    /**
     * {@link AntController#soldierList}
     * @param soldierList 
     */
    public void setSoldierList(CopyOnWriteArrayList<SoldierAnt> soldierList) {
        this.soldierList = soldierList;
    }

    /**
     * {@link AntController#childList}
     * @return 
     *      {@code childList}
     */
    public CopyOnWriteArrayList<ChildAnt> getChildList() {
        return childList;
    }

    /**
     * {@link AntController#childList}
     * @param childList 
     */
    public void setChildList(CopyOnWriteArrayList<ChildAnt> childList) {
        this.childList = childList;
    }

    /**
     * {@link AntController#workerList}
     * @return 
     *      {@code workerList}
     */
    public CopyOnWriteArrayList<WorkerAnt> getWorkerList() {
        return workerList;
    }

    /**
     * {@link AntController#workerList}
     * @param workerList 
     */
    public void setWorkerList(CopyOnWriteArrayList<WorkerAnt> workerList) {
        this.workerList = workerList;
    }

    /**
     * {@link AntController#lookingForFoodList}
     * @return 
     *      {@code lookingForFoodList}
     */
    public CopyOnWriteArrayList<WorkerAnt> getLookingForFoodList() {
        return lookingForFoodList;
    }

    /**
     * {@link AntController#lookingForFoodList}
     * @param lookingForFoodList 
     */
    public void setLookingForFoodList(CopyOnWriteArrayList<WorkerAnt> lookingForFoodList) {
        this.lookingForFoodList = lookingForFoodList;
    }

    /**
     * {@link AntController#carryingFoodList}
     * @return 
     *      {@code carryingFoodList}
     */
    public CopyOnWriteArrayList<WorkerAnt> getCarryingFoodList() {
        return carryingFoodList;
    }

    /**
     * {@link AntController#carryingFoodList}
     * @param carryingFoodList 
     */
    public void setCarryingFoodList(CopyOnWriteArrayList<WorkerAnt> carryingFoodList) {
        this.carryingFoodList = carryingFoodList;
    }

    /**
     * {@link AntController#fightingList}
     * @return 
     *      {@code fightingList}
     */
    public CopyOnWriteArrayList<SoldierAnt> getFightingList() {
        return fightingList;
    }

    /**
     * {@link AntController#fightingList}
     * @param fightingList 
     */
    public void setFightingList(CopyOnWriteArrayList<SoldierAnt> fightingList) {
        this.fightingList = fightingList;
    }
    

    
    /**
     * <b>EN</b>: This method interrupts all soldier and child ant threads existent <br>
     * up to the moment of its invocation and returns the total amount of soldier ants <br>
     * interrupted <br><br>
     * <b>ES</b>: Este método interrumpe todas las hormigas soldado y crías existentes hasta <br>
     * el momento de su invocación y devuelve el número total de hormigas soldado interurmpidas.
     * @return 
     *      {@code int soldiersInterrupted}
     */
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
