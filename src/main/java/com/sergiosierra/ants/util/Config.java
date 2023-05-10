/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ssierra
 */
public class Config {
    
    
    public static final boolean LOG_ENABLED = true;
    public static final String LOG_PATH = "./log/";
    public static String LOG_DATETIME = "unset";
    
    public static final int UPDATE_RATE = 50; // Millis
    public static final int INSTANCES = 10000;
    
    
    public static final String APP_ICON = "./resources/icon.png";
    
    
    public static final int PORT = 25565;
    
    // Sleep timings.
    
    public static final int SEEDER_MIN = 800;
    public static final int SEEDER_OFFSET = 2700;

    
    public Config() {
        
        Date now = new Date();
        String format = "HH:mm:ss_dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        LOG_DATETIME = sdf.format(now);
    
    }
    
}
