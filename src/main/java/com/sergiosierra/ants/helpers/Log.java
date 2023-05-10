/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import com.sergiosierra.ants.util.Config;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * <b>EN</b>: This class provides methods for any {@code Thread} to log to a file. <br><br>
 * <b>ES</b>: Esta clase proporciona métodos para que cualquier {@code Thread} escriba en un archivo de log.
 * @author ssierra
 */
public class Log {
    
    /**
     * <b>EN</b>: This binary semaphore helps ensuring the text file is accessed in mutual exclusion.<br><br>
     * <b>ES</b>: Este semáforo binario se asegura de que el archivo de texto se acceda en exclusión mutua.
     */
    private static Semaphore logMutexSem = new Semaphore(1, true);
    
    /**
     * <b>EN</b>: This method logs a given string into a text file located in ./log/<br><br>
     * <b>ES</b>: Este método registra una cadena de caracteres dada en un archivo de texto ubicado en ./log/.
     * @param str
     * @throws IOException 
     */
    public static void logln(String str) throws IOException {
    
        Date now = new Date();
        String format = "HH:mm:ss:SSSS dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        String formattedContent = sdf.format(now) + " - " + str;
        
        // Access the resource...
        // Note that the DateTime has already been fixed.
        // The actual event time will be shown.
        logMutexSem.acquireUninterruptibly();
        
        if (Config.LOG_CONSOLE_ENABLED) {
        
            System.out.println(formattedContent);
            
        }
        
        
        if (Config.LOG_FILE_ENABLED) {
        
            FileWriter fw = new FileWriter(Config.LOG_PATH + Config.LOG_FILENAME + "_" + Config.LOG_DATETIME + "." + Config.LOG_EXT, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(formattedContent);
            bw.newLine();
            bw.close();
            fw.close();
        
        }

        logMutexSem.release();        
    }
}
