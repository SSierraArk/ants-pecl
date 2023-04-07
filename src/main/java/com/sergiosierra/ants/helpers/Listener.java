/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import com.sergiosierra.ants.models.Colony;
import java.util.ArrayList;

/**
 *
 * @author ssierra
 */
public class Listener extends Thread {
    
    Colony colony;
    
    public Listener(Colony colony) {
    
        this.colony = colony;
    
    }
    
    @Override
    public void run() {

        while(true) {
        
            try {
                sleep(50);
            } catch (InterruptedException e) {
            }
            System.out.println("Inside: " + colony.getInside());
            System.out.println("Eating Zone: " + colony.getEatingZone());
            System.out.println("Food count: " + colony.getEatingZoneFoodCount());
            
        }
    
    }
}
