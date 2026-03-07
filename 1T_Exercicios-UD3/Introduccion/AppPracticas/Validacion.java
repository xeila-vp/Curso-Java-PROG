/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javapracticas;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Validacion {
     public static void ValidarEdad(int edad)throws MiException{ // validación propia
        if(edad<18){
            throw new MiException("Edad no válida. Debes ser mayor de edad.");
        }
    }
     public static void ValidarNombre(String nombre) throws MiException{
        String patron = "^[A-Za-z]{4,}$";
        if(nombre == null){
            throw new MiException("El campo nombre no puede estar vació.");
        }
        if(!nombre.matches(patron)){
            throw new MiException("El campo debe tener mínimo 4 letras sin espacios.");
        }
     }
}
