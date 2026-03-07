/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class MiniDynamicArray {

    private int number;     // número de elementos gardados
    private Object[] array; // array onde se gardan

    // O tamaño inicial pásase no constructor
    public MiniDynamicArray(int initialcap) {
        this.number = 0;                 // aínda non hai elementos
        this.array = new Object[initialcap]; // capacidade inicial
    }

    // Engadir elemento
    public void add(Object o) {
        array[number] = o; // gardamos o obxecto
        number++;          // incrementamos o contador
    }

    // Recuperar elemento
    public Object get(int index) {
        return array[index];
    }

    // Cantos elementos hai gardados
    public int size() {
        return number;
    }

    public static void demo() {
        MiniDynamicArray m = new MiniDynamicArray(10);

        m.add("Ana");
        m.add("Brais");

        System.out.println("Tamaño: " + m.size());
        System.out.println("Primeiro elemento: " + m.get(0) + "\n");
    }
}
