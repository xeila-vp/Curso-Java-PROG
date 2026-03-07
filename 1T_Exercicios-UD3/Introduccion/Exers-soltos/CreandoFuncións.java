/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejers;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class CreandoFuncións {

        
    // 2 --- FUNCIÓN QUE RECIBE UN NUM E RETORNA O PRIMEIRO DIVISOR > 1.
    public static int primeiroDivisor(int n) {  
        
        int divisor = 2;
        
        while( n % divisor != 0 ){
            divisor++;
        }
        return divisor;
    }    
    
    // 4--- MÉTODO ePrimo
    
    public static boolean ePrimo(int p){
        return primeiroDivisor(p) == p;
    }
   
    // --- EJERCICIO 1 --- PROGRAMA QUE PIDE UN NUM E INDICA O PRIMEIRO DIVISOR > 1
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Introduce un número para indicar o primeiro divisor > 1: ");
        int n = scn.nextInt();

        int programaDivisor = 2;
        while (n % programaDivisor != 0) {
            programaDivisor++;
        }
        System.out.println("O primeiro divisor maior de 1 é: " + programaDivisor);

    
    // EJERCICIO 3 ---
    /* PROGRAMA QUE SOLICITA UN NUM E VISUALIZA O PRIMEIRO DIVISOR > 1 UTILIZANDO A FUNCIÓN DO EXER 2.  */

    
        System.out.println("Introduce un Número para visualizar o primeiro divisor (funcion exer 2)");
        int n2 = scn.nextInt();
        
        int funcionDivisor = primeiroDivisor(n2);
        
        System.out.println("O primeiro divisor de " + n2 + " maior de 1 é: " + funcionDivisor);
        
    // EJERCICIO 4 --- 
    /* PROGRAMA QUE SOLICITA UN NUM E DIGA SI É PRIMO OU NON EMPREGANDO A FUNCIÓN 
          DO EXER 2 E DESENVOLVENDO UN MÉTODO EPRIMO QUE INDIQUE SI O N É PRIMPO OU NON.  */    

    
        System.out.println("Introduce un Número para ver si é primo (funcion exer.2) e facer o método EPrimo");
        int n3 = scn.nextInt();
        
        if (ePrimo(n3)){
            System.out.println("O número é primo");
        } else {
            System.out.println("O número non é primo");
        }
        
    // EJERCICIO 5 --- PROGRAMA QUE SOLICITA UN NUM E VISUALIZA OS FACTORES PRIMOS EMPREGANDO A FUNCIÓN DO EXER 2. 
    
    
        System.out.println("Introduce un Número para visualizar os factores primos coa funcion2");
        int n4 = scn.nextInt();
        int contador = 0;
        
        while (n4 > 1){
        
            int factorPrimo = primeiroDivisor(n4);
            contador ++;
            System.out.println("factor " + contador + ": " + factorPrimo);
            n4 = n4 / factorPrimo;
        }
        
    // EXERCICIO 6
    /* PROGRAMA QUE PIDA UN N E VISUALICE A SUMA DOS NÚMEROS PRIMOS 
           ENTRE 1 E N. */
   
    
        System.out.println("Introduce un Número para ver a suma dos números primos de 1 ata él");
        int n5 = scn.nextInt();
        int suma = 0;
        
        for (int i=2 ; i <= n5 ; i++){
            if(ePrimo(i)){
                suma += i;
            }
        }
        System.out.println("A suma dos números primos entre 1 e " + n5 + " é: " + suma);

    
    //EXERCICIO 7
    /* PROGRAMA QUE PIDA UN N E DIGA SI É MAIOR QUE 53400. NON PODE ACEPTAR N PRIMOS, 
          INSISTINDO NA ENTRADA SI O NÚM INTRODUCIDO É PRIMO E DANDO A OPCIÓN DE REMATAR EN 
          LUGAR DE INTRODUCIR OUTRO NUM.  */
            
        System.out.println("Introduce un Número que non sexa primo para saber si é > ou < que 53400");
        int n6=scn.nextInt();
        int numComparacion=53400;
        
        while (ePrimo(n6)){
            System.out.println("No se admiten números primos");
            System.out.print("¿Quieres terminar? s/N: ");
            String resposta = scn.next();
                if (resposta.equalsIgnoreCase("s")){
                System.out.println("Adios");
                return;
                } 
            System.out.println("Introduce outro número");
            n6=scn.nextInt();
        }
        if (n6 < numComparacion){
            System.out.println("O número é menor que " + numComparacion);
        }else{
            System.out.println("O número é maior que " + numComparacion);
        }
    
    }
}    


   
