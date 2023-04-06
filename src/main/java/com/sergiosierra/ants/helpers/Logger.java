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

/**
 *
 * @author ssierra
 */
public class Logger {
    
    public static synchronized void logln(String str) throws IOException {
    
        Date now = new Date();
        String format = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        String formattedContent = sdf.format(now) + " - " + str;
        
        
        System.out.println(str);
        
        
        FileWriter fw = new FileWriter("./", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(formattedContent);
        bw.newLine();
        bw.close();
        fw.close();
        
        
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
