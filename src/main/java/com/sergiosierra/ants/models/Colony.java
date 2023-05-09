/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <b>EN</b>: This class contains thread-safe collections representing all zones and variables <br>
 * related to the colony. <br><br>
 * <b>ES</b>: Esta clase contiene colecciónes thread-safe representando todas las zonas y <br>
 * variables relacionadas a la colonia.
 * @author ssierra
 */
public class Colony implements Cloneable {
    
    /**
     * <b>EN</b>: This attribute takes count of the number of food items stored <br>
     * within the colony's food storage room. Initialised to 0. <br><br>
     * <b>ES</b>: Este atributo lleva la cuenta del número de elementos de comida <br>
     * almacenados en el almacén de comida de la colonia. Inicializado a 0.
     */
    private int foodCount = 0;
    
    /**
     * <b>EN</b>: This attribute takes count of the number of food items stored <br>
     * within the colony's eating zone. Initialised to 0. <br><br>
     * <b>ES</b>: Este atributo lleva la cuenta del número de elementos de comida <br>
     * almacenados en el comedor de la colonia. Inicializado a 0.
     */
    private int eatingZoneFoodCount = 0;
    
    // Zones that are specific to a certain kind of ant.
    /**
     * <b>EN</b>: Collection storing all {@code WorkerAnt} instances within the food storage room. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code WorkerAnt} en el almacén de comida.
     */
    private CopyOnWriteArrayList<WorkerAnt> foodStorage = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: Collection storing all {@code SoldierAnt} instances within the instruction zone. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code SoldierAnt} en la zona de instrucción.
     */
    private CopyOnWriteArrayList<SoldierAnt> instructionZone = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: Collection storing all {@code ChildAnt} instances within the shelter. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code ChildAnt} en el refugio.
     */
    private CopyOnWriteArrayList<ChildAnt> shelter = new CopyOnWriteArrayList<>();
    
    // Zones accessible to all ants.
    
    /**
     * <b>EN</b>: Collection storing all {@code Ant} instances within the eating zone. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code Ant} en el comedor.
     */
    private CopyOnWriteArrayList<Ant> eatingZone = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: Collection storing all {@code Ant} instances within the resting zone. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code Ant} en la zona de descanso.
     */
    private CopyOnWriteArrayList<Ant> restingZone = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: Collection storing all {@code Ant} instances outside of the colony. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code Ant} fuera de la colonia.
     */
    private CopyOnWriteArrayList<Ant> outside = new CopyOnWriteArrayList<>();
    
    /**
     * <b>EN</b>: Collection storing all {@code Ant} instances inside the colony. <br><br>
     * <b>ES</b>: Colección de todos las instancias {@code Ant} dentro de la colonia.
     */
    private CopyOnWriteArrayList<Ant> inside = new CopyOnWriteArrayList<>();
    
    /**
     * {@link Colony#eatingZoneFoodCount}
     * @return 
     *      {@code int eatingZoneFoodCount}
     */
    public int getEatingZoneFoodCount() {
        return eatingZoneFoodCount;
    }

    /**
     * {@link Colony#eatingZoneFoodCount}
     * @param eatingZoneFoodCount 
     */
    public void setEatingZoneFoodCount(int eatingZoneFoodCount) {
        this.eatingZoneFoodCount = eatingZoneFoodCount;
    }

    /**
     * {@link Colony#foodCount}
     * @return 
     *      {@code int foodCount}
     */
    public int getFoodCount() {
        return foodCount;
    }

    /**
     * {@link Colony#foodCount}
     * @param foodCount 
     */
    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    /**
     * {@link Colony#foodStorage}
     * @return 
     *      {@code CopyOnWriteArrayList<WorkerAnt> foodStorage}
     */
    public CopyOnWriteArrayList<WorkerAnt> getFoodStorage() {
        return foodStorage;
    }

    /**
     * {@link Colony#foodStorage}
     * @param foodStorage
     */
    public void setFoodStorage(CopyOnWriteArrayList<WorkerAnt> foodStorage) {
        this.foodStorage = foodStorage;
    }

    /**
     * {@link Colony#instructionZone}
     * @return 
     *      {@code CopyOnWriteArrayList<SoldierAnt> instructionZone}
     */
    public CopyOnWriteArrayList<SoldierAnt> getInstructionZone() {
        return instructionZone;
    }

    /**
     * {@link Colony#instructionZone}
     * @param instructionZone 
     */
    public void setInstructionZone(CopyOnWriteArrayList<SoldierAnt> instructionZone) {
        this.instructionZone = instructionZone;
    }

    /**
     * {@link Colony#shelter}
     * @return 
     *      {@code CopyOnWriteArrayList<ChildAnt> shelter}
     */
    public CopyOnWriteArrayList<ChildAnt> getShelter() {
        return shelter;
    }

    /**
     * {@link Colony#shelter}
     * @param shelter
     */
    public void setShelter(CopyOnWriteArrayList<ChildAnt> shelter) {
        this.shelter = shelter;
    }

    /**
     * {@link Colony#eatingZone}
     * @return 
     *      {@code CopyOnWriteArrayList<Ant> eatingZone}
     */
    public CopyOnWriteArrayList<Ant> getEatingZone() {
        return eatingZone;
    }

    /**
     * {@link Colony#eatingZone}
     * @param eatingZone
     */
    public void setEatingZone(CopyOnWriteArrayList<Ant> eatingZone) {
        this.eatingZone = eatingZone;
    }

    /**
     * {@link Colony#restingZone}
     * @return 
     *      {@code CopyOnWriteArrayList<Ant> restingZone}
     */
    public CopyOnWriteArrayList<Ant> getRestingZone() {
        return restingZone;
    }

    /**
     * {@link Colony#restingZone}
     * @param restingZone
     */
    public void setRestingZone(CopyOnWriteArrayList<Ant> restingZone) {
        this.restingZone = restingZone;
    }

    /**
     * {@link Colony#outside}
     * @return 
     *      {@code CopyOnWriteArrayList<Ant> outside}
     */
    public CopyOnWriteArrayList<Ant> getOutside() {
        return outside;
    }

    /**
     * {@link Colony#outside}
     * @param outside
     */
    public void setOutside(CopyOnWriteArrayList<Ant> outside) {
        this.outside = outside;
    }

    /**
     * {@link Colony#inside}
     * @return 
     *      {@code CopyOnWriteArrayList<Ant> inside}
     */
    public CopyOnWriteArrayList<Ant> getInside() {
        return inside;
    }

    /**
     * {@link Colony#inside}
     * @param inside
     */
    public void setInside(CopyOnWriteArrayList<Ant> inside) {
        this.inside = inside;
    }
    
}
