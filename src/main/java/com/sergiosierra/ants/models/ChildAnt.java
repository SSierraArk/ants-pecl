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
public class ChildAnt extends Ant {
    
    public ChildAnt(Controller controller) {
    
        this.controller = controller;
        this.antId = controller.ant().getChildList().size();
        controller.ant().getChildList().add(this);
    
    }
    
    public String getAntId() {
        return "HC" + String.format("%04d", antId);
    }
    
    @Override
    public String toString() {
    
        return getAntId();
    
    }
    
}
