/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sergiosierra.ants.helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 *
 * @author ssierra
 */
public class Log {
    
    private static Semaphore logMutexSem = new Semaphore(1, true);
    
    public static void logln(String str, boolean output) throws IOException {
    
        logMutexSem.acquireUninterruptibly();
        
        Date now = new Date();
        String format = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        String formattedContent = sdf.format(now) + " - " + str;
        
        
        System.out.println(formattedContent);
        
        if (output) {
        
            FileWriter fw = new FileWriter("./", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(formattedContent);
            bw.newLine();
            bw.close();
            fw.close();
        
        }

        logMutexSem.release();
        System.out.println("Semaphore released");
        
    }
    
    public static synchronized void syncPrintln(String str) {
    
        System.out.println(str);
    
    }
    
    public static synchronized void syncPrintln(String str, Boolean verbose) {
    
        Date now = new Date();
        String format = "HH:mm:ss.SSS dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formattedContent = verbose ? sdf.format(now) + " - " + str : str;
        
        System.out.println(formattedContent);
        
    }
    
    public static void println(String str, Boolean verbose) {
    
        Date now = new Date();
        String format = "HH:mm:ss.SSS dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formattedContent = verbose ? sdf.format(now) + " - " + str : str;
        
        System.out.println(formattedContent);
        
    }
    
    
    
}
