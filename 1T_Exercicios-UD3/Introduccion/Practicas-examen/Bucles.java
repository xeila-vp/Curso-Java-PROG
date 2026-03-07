/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class Bucles {

    public static void demo() {

        // FOR clásico
        for (int i = 0; i < 5; i++) {
            System.out.println("for i = " + i);
        }

        System.out.println();

        // WHILE
        int x = 0;
        while (x < 3) {
            System.out.println("while x = " + x);
            x++;
        }

        System.out.println();

        // DO-WHILE → executa polo menos unha vez
        int y = 0;
        do {
            System.out.println("do-while y = " + y);
            y++;
        } while (y < 3);

        System.out.println();
    }
}
