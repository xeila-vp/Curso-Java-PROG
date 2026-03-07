/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesrodeira.programacion.utils;

/**
 *
 * @author xavi
 */
public class StringUtils {
    private static char[] _vocals="AEIOUaeiouáéíóúÁÉÍÓÚüÜàèìòùÀÈÌÒÙ".toCharArray();
    
    public static boolean isVocal(char c) {
        String vocals="AEIOUaeiouáéíóúÁÉÍÓÚüÜàèìòùÀÈÌÒÙ";
        return vocals.contains(String.valueOf(c));
    }
    
    
    public static boolean isVocal_more_efficient(char c) {
        for(char v:_vocals) {
            if (c==v) return true;
        }
        return false;
    }
    
    public static boolean isVocal_best_solution(char c) {
        return ("AEIOUaeiouáéíóúÁÉÍÓÚüÜàèìòùÀÈÌÒÙ".indexOf(c)!=-1);
    }
    
    
    public static int vocals(String cadea) {
        int count=0;
        for(int c=0;c<cadea.length();c++) if (isVocal(cadea.charAt(c))) count++;
        return count;
    }
    
    public static int vocals_better_version(String cadea) {
        int count=0;
        for(char c: cadea.toCharArray()) if (isVocal_more_efficient(cadea.charAt(c))) count++;
        return count;
    }
    
    
    public static String reverse_manual(String s){ 
        if (s==null) return s;
        char[] letters=s.toCharArray();
        String result="";
        for(char l:letters) result=l+result;
        return result;
    }
    
    public static String reverse(String s){ 
        return new StringBuilder(s).reverse().toString();
    }
    
    public static boolean ePalindroma(String s) {
        if (s==null) return false;
        //return s.replaceAll("\s+","").equals(reverse(s).replaceAll("\s+",""));
        String cleaned=s.replaceAll("\s+","");
        return cleaned.equals(reverse(cleaned));
    }
    
    public static String clean(String s) {
        return s.replaceAll("\\s+"," ").trim();
        //return s.replaceAll("\\s+"," ").replaceAll("^\\s+", "").replaceAll("\\s+$", "");
    }
    
    public static String[] getWords(String s, int min, int max) {
        String separators="[,:;.]";
        String[] words=clean(s.replaceAll(separators," ")).split(" ");
        String result="";
        for(String w: words) {
            if ((w.length()>=min) && (w.length()<=max)) {
                if (result!="") result=result+" ";
                result=result+w;
            }
        }
        return result.split(" ");
    }
    
    public static void main(String[] args) {
        String test="  Esto,e    unha    proba     de    limpeza    con     clean   ";
        System.out.println(clean(test));
        System.out.println(reverse(test));
        System.out.println(reverse_manual(test));
        System.out.println(ePalindroma("esa"));
        System.out.println(ePalindroma("ese"));
        String[] filter=getWords(test,4,5);
        for(String s:filter) System.out.println(s);
    }
}
