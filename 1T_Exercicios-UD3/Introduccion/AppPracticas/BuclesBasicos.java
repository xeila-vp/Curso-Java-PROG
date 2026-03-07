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
public class BuclesBasicos {
    
    //FOR
    public static void BucleContador(){
        int contador=0;
        
        for(int i=1;i<=5;i++){
            System.out.println("num: " + i);
            contador++;
        }System.out.println("total :" + contador);
    }
    //FOR STOP
    public static void BucleStop(){
        System.out.println("Introduce un número");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        
        for(int i=0;i<=num;i++){
            System.out.println(i);
            if(num==i){
                System.out.println("Número encontrado. Stop");
            }
        }
    }
    
    //WHILE
    public static void BucleWhile(){
        
        int num = 18;
        
        while(num % 7 != 0){
            num++;
            System.out.println("Num: " + num);
        }System.out.println("Este es múltiplo: " + num);
    }
}


