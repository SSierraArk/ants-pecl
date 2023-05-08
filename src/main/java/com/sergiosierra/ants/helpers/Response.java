/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ssierra
 */
public class Response <T> {
    
    private HashMap<String, T> payload = new HashMap<>();
    private int status;
    private ArrayList<String> errorBag = new ArrayList<>();

    public Response(int status) {
        this.status = status;
    }
    
    public Response(int status, HashMap<String, T> payload) {
    
        this.status = status;
        this.payload = payload;
    
    }

    public HashMap<String, T> getPayload() {
        return payload;
    }

    public void setPayload(HashMap<String, T> payload) {
        this.payload = payload;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<String> getErrorBag() {
        return errorBag;
    }

    public void setErrorBag(ArrayList<String> errorBag) {
        this.errorBag = errorBag;
    }
    
    public void addError(String error) {
        this.errorBag.add(error);
    }
    
    
    
    
    
}
