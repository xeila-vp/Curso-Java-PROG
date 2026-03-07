/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

import java.util.ArrayList;

/**
 *
 * @author Xeila
 */
public class ArrayListExemplos {

    public static void demo() {

        // Crear ArrayList (lista dinámica)
         ArrayList<String> lista = new ArrayList<>();

        // Engadir elementos
        lista.add("Ana");
        lista.add("Brais");
        lista.add("Carla");

        // Tamaño da lista
        System.out.println("Tamaño: " + lista.size());

        // Acceder a un elemento polo índice
        System.out.println("Primeiro elemento: " + lista.get(0));

        // Modificar un elemento
        lista.set(1, "Bruno");

        // Recorrer a lista
        for (String s : lista) {
            System.out.println("Elemento: " + s);
        }

        System.out.println();
    }
}
