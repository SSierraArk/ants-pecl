/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.control;

import com.sergiosierra.ants.models.ChildAnt;
import com.sergiosierra.ants.models.SoldierAnt;
import com.sergiosierra.ants.models.WorkerAnt;
import java.util.ArrayList;

/**
 *
 * @author ssierra
 */
public class AntController {
    
    
    private ArrayList<SoldierAnt> soldierList = new ArrayList<>();
    private ArrayList<ChildAnt> childList = new ArrayList<>();
    private ArrayList<WorkerAnt> workerList = new ArrayList<>();
    
    

    public ArrayList<SoldierAnt> getSoldierList() {
        return soldierList;
    }

    public void setSoldierList(ArrayList<SoldierAnt> soldierList) {
        this.soldierList = soldierList;
    }

    public ArrayList<ChildAnt> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<ChildAnt> childList) {
        this.childList = childList;
    }

    public ArrayList<WorkerAnt> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(ArrayList<WorkerAnt> workerList) {
        this.workerList = workerList;
    }
    
    
    
}
