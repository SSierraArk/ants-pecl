/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <b>EN</b>: This class represents a response, including programme data, a status code <br>
 * and an error bag with error messages (if any). <br><br>
 * <b>ES</b>: Esta clase representa una respuesta que incluye datos de programa, un código de estado <br>
 * y una estructura para almacenar mensajes de error.
 * @author ssierra
 */
public class Response <T> implements Serializable {
    
    /**
     * <b>EN</b>: Programme data to be carried by the response. <br><br> 
     * <b>ES</b>: Datos de programa para ser transportados por la respuesta.
     */
    private HashMap<String, T> payload = new HashMap<>();
    
    /**
     * <b>EN</b>: Status code of the response. <br><br> 
     * <b>ES</b>: Código de estado de la respuesta.
     */
    private int status;
    
    /**
     * <b>EN</b>: Error bag containing any error messages to be carried. <br><br> 
     * <b>ES</b>: Estructura que contiene cualquier mensaje de error a ser transportado.
     */
    private ArrayList<String> errorBag = new ArrayList<>();

    public Response(int status) {
        this.status = status;
    }
    
    public Response(int status, HashMap<String, T> payload) {
    
        this.status = status;
        this.payload = payload;
    
    }

    /**
     * {@link Response#payload}
     * @return 
     *      {@code HashMap<String, T> payload}
     */
    public HashMap<String, T> getPayload() {
        return payload;
    }

    /**
     * {@link Response#payload}
     * @param payload 
     */
    public void setPayload(HashMap<String, T> payload) {
        this.payload = payload;
    }

    /**
     * {@link Response#status}
     * @return 
     *      {@code HashMap<String, T> payload}
     */
    public int getStatus() {
        return status;
    }

    /**
     * {@link Response#status}
     * @param status 
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * {@link Response#errorBag}
     * @return 
     *      {@code HashMap<String, T> payload}
     */
    public ArrayList<String> getErrorBag() {
        return errorBag;
    }

    /**
     * {@link Response#errorBag}
     * @param errorBag 
     */
    public void setErrorBag(ArrayList<String> errorBag) {
        this.errorBag = errorBag;
    }
    
    /**
     * {@link Response#errorBag}
     * @param error 
     */
    public void addError(String error) {
        this.errorBag.add(error);
    }
    
    /**
     * <b>EN</b>: Flushes the error bag. <br><br>
     * <b>ES</b>: Elimina todos los errores del error bag.
     * {@link Response#errorBag}
     */
    public void flushErrorBag() {
        
        this.errorBag.removeAll(this.errorBag);
    
    }
    
    
    
    
    
}
