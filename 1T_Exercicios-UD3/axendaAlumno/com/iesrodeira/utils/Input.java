/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesrodeira.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Xeila
 */
public class Input {
    
    public static void testNif(String nif) throws IllegalArgumentException{
        
        //array letra a comparar con dni
        char[] letraComparar={'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        
        //nif debe tener 9 letras en total
        if(nif==null || nif.length()!=9){
            throw new IllegalArgumentException ("El Nif debe terner 8 números y 1 letra");   
        }else{
            //extraer parte numérica substring
            String digitosNif = nif.substring(0,8);
            char letraNif = Character.toUpperCase(nif.charAt(8));
            
         try{   
            //convertir a numero
            int numNif = (Integer.parseInt(digitosNif));
            
            //saber resto que es la posición del dígito a comparar
            int resto=numNif%23;
            
            //si la posición de la letra a comparar en la posición del resto es distinta de la letra del dni
            if(letraComparar[resto]!=letraNif){
                throw new IllegalArgumentException("DNI inválido. A letra non coincide");
            }
            }catch(NumberFormatException e){
                //capturar errores de conversión
                throw new IllegalArgumentException("DNI non válido");
            }
        }
    }
    //definir patron regex mail e telefono
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    private static final String REGEX_PHONE = "\\d{9}";
    
    //método de validación mail
    public static void testEmail(String email)throws IllegalArgumentException{
        //comprobar si es nulo o está vacío enviar excepción
        if(email==null || email.isEmpty()){
            throw new IllegalArgumentException("No has introducido un email");
        }else{
            //comprobar si no coincide con regex enviar error
            if(!email.matches(REGEX_EMAIL)){
                throw new IllegalArgumentException("Formato de email incorrecto");
            }
        }
    }
    //método de validación telefono
    public static void testPhone(String phone) throws IllegalArgumentException{
        //comprobar si es nulo o está vacío enviar excepción
        if(phone==null || phone.isEmpty()){
            throw new IllegalArgumentException("No has introducido un teléfono");
        }else{
            //comprobar si no coincide con regex enviar error
            if(!phone.matches(REGEX_PHONE)){
                throw new IllegalArgumentException("Formato de teléfono incorrecto");
            }
        }
    }
    //Scanner compartido
    private static final Scanner sc = new Scanner(System.in);
    
    //Método readText CancelException
    public static String readText(String title) throws CancelException{
        System.out.print(title + "(* para cancelar): ");
        String entrada = sc.nextLine();
        
        if (entrada.equals("*")){
            throw new CancelException("Operación cancelada polo usuario.");
        }return entrada;
    }
    
    //Método readText CancelException enter=default
    public static String readText(String title, String introDefault) throws CancelException{
        System.out.print(title + " -" + introDefault + "- " + "(* para cancelar): ");
        String entrada = sc.nextLine();
        
        if (entrada.equals("*")){
            throw new CancelException("Operación cancelada polo usuario.");
        }
        if (entrada.isEmpty()){
            return introDefault;
        }
        return entrada;
    }
    
    //Método readPhone(String title) CancelException
    public static String readPhone(String title) throws CancelException{
        while (true){ //bucle ata que introduza un telefono válido
            
            String entrada = readText(title);//pedimos o telefono (si * cancela)
            
            try{
                testPhone(entrada); //metodo que valida si o formato é correcto
                return entrada; 
            }catch (IllegalArgumentException e){//si non é correcto salta erro e volve o bucle
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    
    //Método readPhone(String title, String introDefault) CancelException
    public static String readPhone(String title, String introDefault) throws CancelException{
        while (true){ //bucle ata que introduza un telefono válido
            
            String entrada = readText(title, introDefault);//pedimos o telefono (si * cancela e si intro=default)
            
            try{
                testPhone(entrada); //metodo que valida si o formato é correcto
                return entrada; 
            }catch (IllegalArgumentException e){//si non é correcto salta erro e volve o bucle
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    
    //Método readEmail(String title) CancelException
    public static String readMail(String title) throws CancelException{
        while (true){ //bucle ata que introduza mail axeitado
            
            String entrada = readText(title); //pedimos mail e si * cancela
        try{
            testEmail(entrada); //comprobamos formato co método testEmail
            return entrada;
        }catch (IllegalArgumentException e){ //si non ten formato correcto, lanzamos erro
            System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    
    //Método readMail (String title, String introDefault) throws CancelException
    public static String readMail(String title, String introDefault) throws CancelException{
        while (true){
            String entrada = readText(title, introDefault);
        try{
            testEmail(entrada);
            return entrada;
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    //Método option
    public static String option(String title,String validos) throws CancelException{
        while(true){
            String entrada = readText(title + " (" + validos+ " )"); //pide o title
            
            if(validos.toUpperCase().contains(entrada.toUpperCase())){
                return entrada;
            }else{
                System.out.println("Opción incorrecta: Escolle unha opción válida.");
            }
        }
    }
    
    //metodo boolean areYouSure()
    public static boolean areYouSure() throws CancelException{
        while(true){

            String entrada = option("Está vostede seguro?", "S/N");
            
            if(entrada.toUpperCase().contains("S")){
                return true;
            }else if(entrada.toUpperCase().contains("N")){
                return false;
            }else{
                System.out.println("Opción incorrecta. Escolle unha opción válida(S/N): ");
            }
        }
    }
    
    //metodo boolean waitEnter(String message)
    public static boolean waitEnter(String message){
        String entrada="x";
        
        while (!entrada.isEmpty()){
            System.out.println(message);
            entrada = sc.nextLine(); // espera entrada do usuario
        }  // repite mentres escriba algo
  
        return true; // sae cando pulsa só Enter
    }
    //metodo boolean waitEnter(String message)
    public static boolean waitEnter(){
        String entrada = "x";

        while(!entrada.isEmpty()){
            System.out.println("Para continuar pulsa Enter");
            entrada = sc.nextLine();
        }
    return true;
    }                                                               
}
