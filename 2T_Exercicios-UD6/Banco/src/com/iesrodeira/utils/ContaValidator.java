/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesrodeira.utils;

/*
* Expresión regular para validar un IBAN de forma básica.
*/
public class ContaValidator {
    
/**
     * Expresión regular para validar o número de conta.
     *
     * @param numConta número de conta a validar
     * @throws IllegalArgumentException se o formato é incorrecto
     */
    private static final String REGEX_IBAN = "^[A-Z]{2}[0-9]{2}[A-Z0-9]{11,30}$";
    
    public static void testConta(String numConta) throws IllegalArgumentException{
        
        // Comproba se é nulo ou baleiro
        
        if (numConta == null || numConta.replaceAll("\\s+", "").isEmpty()){
            throw new IllegalArgumentException("Non se atopou ningún número de conta");
        }
        
        numConta = numConta.toUpperCase();
        
        //Si non coincide
        
        if(!numConta.matches(REGEX_IBAN)){
            throw new IllegalArgumentException("Formato de IBAN incorrecto.");
        } 
    }
}
