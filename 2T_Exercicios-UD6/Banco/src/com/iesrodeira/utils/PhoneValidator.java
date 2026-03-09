/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesrodeira.utils;

/**
 *
 * @author Usuario
 */
public class PhoneValidator {

    /**
            * Expresión regular para validar números de teléfono españoles. Formato: exactamente 9 díxitos.
            * @param phone O número de teléfono a validar
            * @throws IllegalArgumentException Se o teléfono non cumpre co formato coa mensaxe "teléfono non válido"
            */
    private static final String REGEX_PHONE = "\\d{9}";
    
    public static void testPhone(String phone) throws IllegalArgumentException {
        if (phone == null || !phone.replaceAll("\\s+", "").matches(REGEX_PHONE)) {
            throw new IllegalArgumentException("teléfono no valido");
        }
    }
}
