/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author xavi
 */
public class CommandParser {
    public static ArrayList<String> parse(String cmdline) {
        ArrayList<String> args=new ArrayList<>();
        String[] partes=cmdline.trim().split(" ");
        for(int idx=0;idx<partes.length;idx++) {
            args.add(partes[idx].trim().toUpperCase());
        }
        return args;
    }
}
