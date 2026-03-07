
package arrays;

public class Acceso {
    public static void main(String[] args) {
        int[] numeros = {10, 20, 30, 40, 50};

        System.out.println("Primeiro elemento: " + numeros[0]);
        System.out.println("Terceiro elemento: " + numeros[2]);

        numeros[1] = 25; /// Modificación do segundo elemento
        System.out.println("Segundo elemento modificado: " + numeros[1]);
    }
}

