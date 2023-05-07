/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.Colony;
import java.util.HashMap;

/**
 *
 * @author ssierra
 */
public class Listener extends Thread {
    
    Colony colony;
    
    public Listener(Colony colony) {
    
        this.colony = colony;
    
    }
    
    public HashMap<String, String> getData() {
    
        HashMap<String, String> response = new HashMap<>();
        
        response.put("eatingZone", colony.getEatingZone().toString());
        response.put("foodStorage", colony.getFoodStorage().toString());
        response.put("instructionZone", colony.getInstructionZone().toString());
        response.put("restingZone", colony.getRestingZone().toString());
        response.put("shelter", colony.getShelter().toString());
        response.put("outside", colony.getOutside().toString());
        response.put("inside", colony.getInside().toString());
        
        
        return response;
    
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
