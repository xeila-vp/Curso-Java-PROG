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
public class ErroresBasicos{
    
    
    public static void capturarError(){ // leer, capturar InputMismatchException, repetir. (validación de java)
        Scanner sc=new Scanner(System.in);
        boolean valido=false;
        
        while(!valido){
            System.out.println("Escribe un número del 1 al 5.(0 para parar)");
            try{
                int num=sc.nextInt();
                if(num>5 || num<0){
                    System.out.println("Introduce un número entero válido.");
                }
                if(num>0 && num<=5){
                    System.out.println("Número elegido: " + num);
                    valido=true;
                }
                if(num==0){
                    System.out.println("Stop! Has salido del programa");
                    break;
                }
            }catch(Exception e){
                System.out.println("No es un número.");
                sc.nextLine();
            }
        }
    }
    public static void campoEdad() throws MiException{
        boolean valido=false;
        Scanner sc = new Scanner(System.in);
        
        while(!valido){
            System.out.println("Introduce tu edad");
            
            try{
                int edad = sc.nextInt();
                Validacion.ValidarEdad(edad);
                System.out.println("Mayor de edad. Acceso Permitido.");
                valido=true;
            }catch(MiException e){
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println("Introduce un número.");
                sc.nextLine();
            }
        }
    }
    public static void campoNombre() throws MiException{
        Scanner sc = new Scanner(System.in);
        boolean nombreValido=false;
        
        while(!nombreValido){
            System.out.println("Nombre: ");
            
            try{
                String nombre = sc.nextLine();
                Validacion.ValidarNombre(nombre);
                System.out.println("Nombre validado.");
                nombreValido=true;
            }catch(MiException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
