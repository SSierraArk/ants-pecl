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
public class WorkerAnt extends Ant {
    
    
    
    public WorkerAnt(Controller controller) {
    
        this.antId = controller.getWorkerList().size();
        
    }
    
    @Override
    public void run() {
    
        
    
    }

    public String getAntId() {
        return "HO" + String.format("%04d", antId);
    }
    
    
    
}
