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
    
    // Application gobal variables
    
    public static final boolean LOG_FILE_ENABLED = true;
    public static final boolean LOG_CONSOLE_ENABLED = true;
    public static final String LOG_FILENAME = "log"; 
    public static final String LOG_EXT = "txt"; 
    public static final String LOG_PATH = "./log/";
    public static String LOG_DATETIME = "unset";
    
    public static final int UPDATE_RATE = 50; // Millis
    public static final int INSTANCES = 10000;
    
    
    public static final String APP_ICON = "./resources/icon.png";
    
    
    public static final int PORT = 25565;
    
    // Ant behaviours
    // All times for ant behaviours expressed in millis.
    
    public static final int ANT_SOLDIER_MAX_ITERS = 6;
    public static final int ANT_SOLDIER_EATING_TIME = 3000;
    public static final int ANT_SOLDIER_TRAINING_TIME = 2000;
    public static final int ANT_SOLDIER_TRAINING_TIME_OFFSET = 6000;
    public static final int ANT_SOLDIER_RESTING_TIME = 2000;
    public static final int ANT_SOLDIER_FOOD_CONSUMED = 1;

    public static final int ANT_WORKER_MAX_ITERS = 10;
    public static final int ANT_WORKER_EATING_TIME = 3000;
    public static final int ANT_WORKER_RESTING_TIME = 1000;
    public static final int ANT_WORKER_FOOD_CONSUMED = 1;
    
    public static final int ANT_CHILD_EATING_TIME = 3000;
    public static final int ANT_CHILD_EATING_TIME_OFFSET = 2000;
    public static final int ANT_CHILD_RESTING_TIME = 4000;
    public static final int ANT_CHILD_FOOD_CONSUMED = 1;
    
    // Colony specs.
    
    public static final int COL_ENTRANCES = 1;
    public static final int COL_EXITS = 2;
    public static final int COL_FOODSTORAGE = 10;
    public static final int COL_FOODSTORAGE_TIMEOUT = 2;    // Seconds
    public static final int COL_ENTRANCE_DELAY = 100;       // Millis
    
    public static final int THREAT_DURATION = 20000;
    
    // Seeder timings.
    
    public static final int SEEDER_MIN = 800;
    public static final int SEEDER_OFFSET = 2700;

    
    public Config() {
        
        Date now = new Date();
        String format = "HH:mm:ss_dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        LOG_DATETIME = sdf.format(now);
    
    }
    
}
