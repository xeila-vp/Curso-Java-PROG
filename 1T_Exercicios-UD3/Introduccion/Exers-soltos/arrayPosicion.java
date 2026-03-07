/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejers;

/**
 *
 * @author Xeila
 */
public class arrayPosicion {
    public static int buscarValor(int[] array, int nbuscar){
        
        int contador=0;
        
        for(int i=0 ; i<array.length; i++){
            if(array[i]==nbuscar){
                return i;
            }
        }return -1;
    }
    
    public static void main(String[] args) {
        int[] numerosB = new int[]{7,9,0,2};
        int nbuscar=2;
        
        int resultado = buscarValor(numerosB, nbuscar);
        System.out.println("resultado: posicion = " + resultado);
        
    }
}
    
