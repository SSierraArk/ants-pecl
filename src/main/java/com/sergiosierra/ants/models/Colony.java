/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import java.util.ArrayList;

/**
 *
 * @author ssierra
 */
public class Colony {
    
    private int foodCount = 0;
    private int eatingZoneFoodCount = 0;
    
    // Zones that are specific to a certain kind of ant.
    private ArrayList<WorkerAnt> foodStorage = new ArrayList<>();
    private ArrayList<SoldierAnt> instructionZone = new ArrayList<>();
    private ArrayList<ChildAnt> shelter = new ArrayList<>();
    
    // Zones accessible to all ants.
    private ArrayList<Ant> eatingZone = new ArrayList<>();
    private ArrayList<Ant> restingZone = new ArrayList<>();
    private ArrayList<Ant> outside = new ArrayList<>();
    private ArrayList<Ant> inside = new ArrayList<>();

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

    public ArrayList<WorkerAnt> getFoodStorage() {
        return foodStorage;
    }

    public ArrayList<SoldierAnt> getInstructionZone() {
        return instructionZone;
    }

    public ArrayList<ChildAnt> getShelter() {
        return shelter;
    }

    public ArrayList<Ant> getEatingZone() {
        return eatingZone;
    }

    public ArrayList<Ant> getRestingZone() {
        return restingZone;
    }

    public ArrayList<Ant> getOutside() {
        return outside;
    }

    public ArrayList<Ant> getInside() {
        return inside;
    }
    
    

    
}
