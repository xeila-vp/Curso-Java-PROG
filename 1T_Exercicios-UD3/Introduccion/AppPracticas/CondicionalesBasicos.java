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
public class CondicionalesBasicos {
    public static void CondicionalesNota(){
        Scanner sc=new Scanner(System.in);

        System.out.println("Nota Ana: ");
        int nota=sc.nextInt();

        if(nota <=10){
            if(nota > 4 && nota <=10){
                if(nota==5){
                    System.out.println("Ana está aprobada:Suficiente.");
                }
                if(nota==6 || nota==7){
                    System.out.println("Ana está aprobada:Bien.");
                }
                if(nota==8){
                    System.out.println("Ana está aprobada:Notable.");
                }
                if(nota==9 || nota==10){
                    System.out.println("Ana está aprobada:Sobresaliente.");
                }
            }else{
                System.out.println("Ana está suspensa.");
            }
            
        }else{System.out.println("Introduce una nota válida.");}
    }
}
