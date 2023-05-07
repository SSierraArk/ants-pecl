/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sergiosierra.ants.models;

import com.sergiosierra.ants.control.Controller;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ssierra
 */
public class WorkerAntTest {
    
    private Colony colony = new Colony();
    private Controller controller = new Controller(colony);
    
    public WorkerAntTest() {
    }

    /**
     * Test of getAntId method, of class ChildAnt.
     * Checks that the obtained id starts with HC as required
     */
    @Test
    public void testAntId() {
        System.out.println("getAntId");
        
        WorkerAnt instance = controller.spawnWorkerAnt();
        String expResult = "HO";
        String result = instance.getAntId().substring(0, 2);
        assertEquals(expResult, result);
    }
    
}
