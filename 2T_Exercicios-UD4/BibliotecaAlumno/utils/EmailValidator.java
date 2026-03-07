/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Usuario
 */
public class EmailValidator {
    
    //patron regex para email
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    
    //metodo de validación
    public static void TestEmail(String email) throws IllegalArgumentException{
        //comprobar si está vacío
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Non se introduciu ninún email.");
        }
        //comprobar formato
        if(!email.matches(REGEX_EMAIL)){
            throw new IllegalArgumentException("Formato de email incorrecto.");
        }
    }
}
