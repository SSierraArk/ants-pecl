/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sergiosierra.ants.interfaces;

import com.sergiosierra.ants.control.Controller;

/**
 * <b>EN</b>: This interface provides techniques to see if the programme is paused. <br>
 * and respond accordingly. <br><br>
 * <b>ES</b>: Esta interfaz proporciona técnicas para comprobar si el programa se encuentra <br>
 * en pausa y responder de acuerdo a dicho estado.
 * @author ssierra
 */
public interface Pausable {
    
    /**
     * EN: Checks the controller isPaused flag to see whether it should
     * pause its execution or not and pauses if set to true.
     * ES: Comprueba el flag del controlador "isPaused" para comprobar si
     * debería pararse o no y se pausa en caso afirmativo.
     * @param controller 
     */
    public void checkPaused(Controller controller);
    
}
