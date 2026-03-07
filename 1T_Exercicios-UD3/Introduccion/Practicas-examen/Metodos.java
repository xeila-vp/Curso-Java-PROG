/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class Metodos {

    // Método estático → pódese chamar sen crear obxecto
    public static int sumar(int a, int b) {
        return a + b;
    }

    // Método NON estático → é preciso crear un obxecto
    public int restar(int a, int b) {
        return a - b;
    }

    public static void demo() {

        // Chamada a método estático
        int r1 = sumar(5, 3);
        System.out.println("Suma = " + r1);

        // Método de instancia
        Metodos m = new Metodos();
        int r2 = m.restar(10, 4);
        System.out.println("Resta = " + r2 + "\n");
    }
}
