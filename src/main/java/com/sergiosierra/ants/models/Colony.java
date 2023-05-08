/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author ssierra
 */
public class Colony implements Cloneable {
    
    private int foodCount = 0;
    private int eatingZoneFoodCount = 0;
    
    // Zones that are specific to a certain kind of ant.
    private CopyOnWriteArrayList<WorkerAnt> foodStorage = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<SoldierAnt> instructionZone = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ChildAnt> shelter = new CopyOnWriteArrayList<>();
    
    // Zones accessible to all ants.
    private CopyOnWriteArrayList<Ant> eatingZone = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Ant> restingZone = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Ant> outside = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Ant> inside = new CopyOnWriteArrayList<>();

    public int getEatingZoneFoodCount() {
        return eatingZoneFoodCount;
    }

    public void setEatingZoneFoodCount(int eatingZoneFoodCount) {
        this.eatingZoneFoodCount = eatingZoneFoodCount;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public CopyOnWriteArrayList<WorkerAnt> getFoodStorage() {
        return foodStorage;
    }

    public void setFoodStorage(CopyOnWriteArrayList<WorkerAnt> foodStorage) {
        this.foodStorage = foodStorage;
    }

    public CopyOnWriteArrayList<SoldierAnt> getInstructionZone() {
        return instructionZone;
    }

    public void setInstructionZone(CopyOnWriteArrayList<SoldierAnt> instructionZone) {
        this.instructionZone = instructionZone;
    }

    public CopyOnWriteArrayList<ChildAnt> getShelter() {
        return shelter;
    }

    public void setShelter(CopyOnWriteArrayList<ChildAnt> shelter) {
        this.shelter = shelter;
    }

    public CopyOnWriteArrayList<Ant> getEatingZone() {
        return eatingZone;
    }

    public void setEatingZone(CopyOnWriteArrayList<Ant> eatingZone) {
        this.eatingZone = eatingZone;
    }

    public CopyOnWriteArrayList<Ant> getRestingZone() {
        return restingZone;
    }

    public void setRestingZone(CopyOnWriteArrayList<Ant> restingZone) {
        this.restingZone = restingZone;
    }

    public CopyOnWriteArrayList<Ant> getOutside() {
        return outside;
    }

    public void setOutside(CopyOnWriteArrayList<Ant> outside) {
        this.outside = outside;
    }

    public CopyOnWriteArrayList<Ant> getInside() {
        return inside;
    }

    public void setInside(CopyOnWriteArrayList<Ant> inside) {
        this.inside = inside;
    }
    
}
