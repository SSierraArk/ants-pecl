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
public class SoldierAnt extends Ant {
    
    
    public SoldierAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getSoldierList().size();
        controller.ant().getSoldierList().add(this);
    
    }
    
    public String getAntId() {
        return "HS" + String.format("%04d", antId);
    }
    
}
