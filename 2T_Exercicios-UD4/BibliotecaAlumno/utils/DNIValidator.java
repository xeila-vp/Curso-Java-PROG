/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Usuario
 */
public class DNIValidator {
    
    //letras a comparar
    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";
    
    //metodo para validar
    public static void TestDNI(String DNI) throws IllegalArgumentException{
        
        //si é nulo ou vacío
        if(DNI == null || DNI.isEmpty()){
            throw new IllegalArgumentException("Non se introduciu ningún DNI.");
        }
        
        DNI = DNI.toUpperCase();
        
        //Si non ten formato de 8 dixitos e 1 letra
        if(!DNI.matches("\\d{8}+[A-Z]")){
            throw new IllegalArgumentException("Formato de email incorrecto. Debe ter 8 díxitos e 1 letra.");
        }
        
        //Separar numeros de letras
        String nums = DNI.substring(0, 8);
        char letter = DNI.charAt(8);
        
        //calcular letra
        int number = Integer.parseInt(nums);
        char letterTrue = LETTERS.charAt(number % 23);
        
        //COMPARAR
        if(letter != letterTrue){
            throw new IllegalArgumentException ("DNI non válido. A letra non coincide.");
        }
    }
}

