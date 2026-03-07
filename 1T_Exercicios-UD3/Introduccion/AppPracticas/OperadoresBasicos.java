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
public class OperadoresBasicos {
    public static void OpBasicos(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce un número: ");
        int a= sc.nextInt();
        
        System.out.println("Introduce outro número: ");
        int b=sc.nextInt();
        
        int suma=a+b;
        int resta=a-b;
        int division=a/b;
        int multiplicacion = a*b;
        
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("División: " + division);
        System.out.println("Multiplicación: " + multiplicacion);
    }
}
