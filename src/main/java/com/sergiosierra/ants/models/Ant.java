/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;

/**
 *
 * @author ssierra
 */
public class Ant extends Thread {
   
    protected Controller controller;
    protected int antId;
    protected int holdedItems = 0;

    public int getHoldedItems() {
        return holdedItems;
    }

    public void setHoldedItems(int holdedItems) {
        this.holdedItems = holdedItems;
    }
    
}
