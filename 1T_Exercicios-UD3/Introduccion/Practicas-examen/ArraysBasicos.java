/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class ArraysBasicos {

    public static void demo() {

        // ===== 1) DECLARAR + CREAR ARRAY =====
        int[] numeros = new int[5]; // tamaño fixo: 5 posicións

        // ===== 2) ASIGNAR VALORES =====
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;
        numeros[3] = 40;
        numeros[4] = 50;

        // ===== 3) RECORRER UN ARRAY =====
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("numeros[" + i + "] = " + numeros[i]);
        }

        System.out.println();

        // ===== 4) INICIALIZAR DIRECTAMENTE =====
        String[] nomes = { "Ana", "Brais", "Carla" };

        // ===== 5) FOR-EACH =====
        for (String n : nomes) {
            System.out.println("Nome: " + n);
        }

        System.out.println();
    }
}
