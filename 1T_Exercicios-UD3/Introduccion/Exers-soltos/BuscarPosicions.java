/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejers;

/**
 *
 * @author Xeila
 */
public class BuscarPosicions {

    // Método que devolve un array coas posicións onde aparece o número
    public static int[] buscarPosicions(int[] array, int nbuscar) {

        int contador = 0;

        // 1) Primeiro contamos cantas veces aparece
        for (int i = 0; i < array.length; i++) {
            if (array[i] == nbuscar) {
                contador++;
            }
        }
        // Se non aparece, devolvemos array baleiro
        if (contador == 0) {
            return new int[0];
        }
        // 2) Creamos array do tamaño exacto
        int[] posicions = new int[contador];

        // 3) Enchémolo coas posicións atopadas
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == nbuscar) {
                posicions[index] = i;
                index++;
            }
        }
        return posicions;
    }

    public static void main(String[] args) {

        int[] numeros = {5, 7, 2, 7, 9, 7};
        int nbuscar = 7;

        int[] atopadas = buscarPosicions(numeros, nbuscar);

        // imprimir o array de posicións
        System.out.println("Posicións atopadas:");
        for (int i = 0; i < atopadas.length; i++) {
            System.out.println(atopadas[i]);
        }
    }
}

