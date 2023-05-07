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
public class ChildAntTest {
    
    private Colony colony = new Colony();
    private Controller controller = new Controller(colony);
    
    public ChildAntTest() {
    }


    /**
     * Test of getAntId method, of class ChildAnt.
     * Checks that the obtained id starts with HC as required
     */
    @Test
    public void testAntId() {
        System.out.println("getAntId");
        
        ChildAnt instance = controller.spawnChildAnt();
        String expResult = "HC";
        String result = instance.getAntId().substring(0, 2);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNormalBehaviour() {
        System.out.println("Test normal ChildAnt behaviour...");
        
        ChildAnt instance = controller.spawnChildAnt();
        instance.start();
        
    
    }
    
    public static void main(String[] args) {
    
        Colony colony = new Colony();
        colony.setEatingZoneFoodCount(10);
        Controller controller = new Controller(colony);
        ChildAnt one = controller.spawnChildAnt();
        ChildAnt two = controller.spawnChildAnt();
        ChildAnt three = controller.spawnChildAnt();
        one.start();
        two.start();
        three.start();
    }

    
}
