/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.helpers.Pausable;

/**
 * <b>EN</b>: Parent class that models an ant. <br><br>
 * <b>ES</b>: Superclase que modela una hormiga.
 * @author ssierra
 */
public class Ant extends Thread implements Pausable {
   
    /**
     * <b>EN</b>: The application main {@code Controller}. <br><br>
     * <b>ES</b>: El {@code Controller} principal de la aplicación.
     */
    protected Controller controller;
    
    /**
     * <b>EN</b>: This attribute represents the ant id. It's a numeric field. <br><br>
     * <b>ES</b>: Este atributo representa el id de hormiga. Es un campo numérico.
     */
    protected int antId;
    
    /**
     * <b>EN</b>: This attribute represents the total amount of items that an <br>
     * {@code Ant} instance is holding. Initialised to 0.<br><br>
     * <b>ES</b>: Este atributo representa la cantidad total de objetos que una <br>
     * instancia {@code Ant} está sosteniendo. Inicializado a 0.
     */
    protected int holdedItems = 0;
    
    /**
     * <b>EN</b>: This attribute takes count of the times the run method has been <br>
     * executed. Initialised to 0. <br><br>
     * <b>ES</b>: Este atributo cuenta las veces que el método run se ejecuta. Inicializado a 0.
     */
    protected int execCounter = 0;

    @Override
    public void checkPaused(Controller controller) {
    
        if (controller.isPaused()) {
        
            controller.pauseSem().acquireUninterruptibly();
        
        }
    
    }

    /**
     * {@link Ant#holdedItems}
     * @return 
     *      {@code int holdedItems}
     */
    public int getHoldedItems() {
        return holdedItems;
    }
    
    /**
     * {@link Ant#holdedItems}
     * @param holdedItems 
     */
    public void setHoldedItems(int holdedItems) {
        this.holdedItems = holdedItems;
    }
    
    /**
     * <b>EN</b>: This method removes {@code amount} elements from {@code holdedItems} <br><br>
     * <b>ES</b>: Este método elimina {@code amount} elementos de {@code holdedItems}
     * @param amount 
     */
    public void dropItems(int amount) {
    
        if(holdedItems >= amount){
        
            holdedItems -= amount;
        
        }
    
    }
    
    /**
     * <b>EN</b>: This method adds {@code amount} elements to {@code holdedItems} <br><br>
     * <b>ES</b>: Este método añade {@code amount} elementos a {@code holdedItems}
     * @param amount 
     */
    public void holdItems(int amount) {
    
        holdedItems += amount;
    
    }
    
}
