/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javapracticas;

/**
 *
 * @author Usuario
 */
public class MiException extends Exception{
    //constructor
    public MiException (String mensaje){
        super(mensaje);//pasa el texto al constructor para crear el mensaje de error
    }
}
