/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javapracticas;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class AppPracticas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MiException {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("-----Menú practicas-----");
        System.out.println("1. Operadores básicos.");
        System.out.println("2. Condicionales básicos.");
        System.out.println("3. Bucles básicos.");
        System.out.println("4. Errores y excepciones");
        
        int opcion=sc.nextInt();
        
        switch(opcion){
            case 1: 
            System.out.println("--Operadores básicos--");
            OperadoresBasicos.OpBasicos();
            break;
            
            case 2:
                System.out.println("---Condicionales Básicos---");
                CondicionalesBasicos.CondicionalesNota();
                break;
            case 3:
                System.out.println("---Bucles básicos---");
                System.out.println("--FOR + CONTADOR (1 a 5)--");
                BuclesBasicos.BucleContador();
                System.out.println("--WHILE-- (1er multiplo de 7)");
                BuclesBasicos.BucleWhile();
                System.out.println("--FOR-- (num stop)");
                BuclesBasicos.BucleStop();
                break;
            case 4:
                System.out.println("---EXCEPCIONES Y ERRORES---");
                System.out.println("-- Capturar error --");
                ErroresBasicos.capturarError();
                System.out.println("-- Comprobar edad --");
                ErroresBasicos.campoEdad();
                System.out.println("-- Comprobar nombre --");
                ErroresBasicos.campoNombre();
                break;
        }
    }
}
