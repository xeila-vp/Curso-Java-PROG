/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejers;

/**
 *
 * @author Xeila
 */
public class arrays_metodoSuma {

    /**
     * @param args the command line arguments
     */
    public static int Suma(int[] array){
        int suma=0;
        
        for(int i=0;i<array.length;i++){
            suma=array[i]+suma;
        }
        return suma;
    }
    public static void main(String[] args) {
        
        //array inicializado e declarado por separado
        
        int [] arraynum;
        arraynum = new int[5];

        //array inicializado e declarado xunto
        
        int[] numeros = {10, 20, 30, 40, 0};
        
        //array inicializado e declarado por separado
        
        int posContador=0;
        
        arraynum[posContador++]= 5;
        arraynum[posContador++]= 4;
        arraynum[posContador++]= 6;
        
        System.out.println("Numeros en array: " + posContador);
        
        System.out.println("Tamaño array: " + arraynum.length);
        
        for(int i=0;i<arraynum.length;i++){
            System.out.println("posición: " + i + " num: " + arraynum[i]);
        }
        
        int contador2=0;
        
        for(int i=0;i<numeros.length;i++){
            System.out.println("Numeros en array: " + numeros[i]);
            if(numeros[i]!=0){
                contador2++;
            }
        }
        System.out.println("Suma array1: " + Suma(arraynum));
        System.out.println("numeros en array2: " + contador2);
    }
    
}
