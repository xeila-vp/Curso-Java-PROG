/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesrodeira.utils;

/**
 *
 * @author Xeila
 */

//Herdanza básica: Crear unha excepción vixiada que herda de Exception
public class CancelException extends Exception{
    public CancelException(){
        //Constructor sen argumentos que usa mensaxe estándar. (Super chama á clase pai Exception)
        super("Operación cancelada polo usuario.");
    }
    //Constructor con mensaxe personalizada.
    public CancelException(String mensaje){
        super(mensaje);
    }

}
