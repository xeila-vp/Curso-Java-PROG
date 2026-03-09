/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesrodeira.utils;

/**
 *
 * @author Usuario
 *
 * Valida un NIF (Número de Identificación Fiscal) español.
 */
public class DniValidator {

    /**
           * Expresión regular para validar números dni español. 
           *@param dni a validar
           * @throws IllegalArgumentException se o formato é incorrecto
           */
    private static final String REGEX_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    //metodo para validar
    public static void testDNI(String dni) throws IllegalArgumentException {

        //si é nulo ou vacío
        if (dni == null || dni.replaceAll("\\s+", "").isEmpty()) {
            throw new IllegalArgumentException("Non se introduciu ningún DNI.");
        }
        dni = dni.toUpperCase();

        //Si non ten formato de 8 dixitos e 1 letra
        if (!dni.matches("\\d{8}+[A-Z]")) {
            throw new IllegalArgumentException("Formato de email incorrecto. Debe ter 8 díxitos e 1 letra.");
        }

        //Separar numeros de letras
        String nums = dni.substring(0, 8);
        char letter = dni.charAt(8);

        //calcular letra
        int number = Integer.parseInt(nums);
        char letterTrue = REGEX_DNI.charAt(number % 23);

        //COMPARAR
        if (letter != letterTrue) {
            throw new IllegalArgumentException("DNI non válido. A letra non coincide.");
        }
    }
}
