/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 * 
 */
public class ValidarNif {
    
    //Esto faise na clase Cliente
    //Constructor que indica os parámetros mínimos para crear o obxeto

 // Método estático que devolve true se o NIF é válido
    public static boolean validarNif(String nif) {

        // Eliminamos espazos ao principio e final
        nif = nif.trim();

        // Un NIF válido ten 8 díxitos + 1 letra → total 9 caracteres
        if (nif.length() != 9) {
            return false;
        }

        // Collemos a parte numérica (os 8 primeiros caracteres)
        String parteNumerica = nif.substring(0, 8);

        // Collemos a letra final
        char letra = Character.toUpperCase(nif.charAt(8));

        // Probamos que os 8 primeiros caracteres sexan números
        // Se non se pode converter, o NIF non é válido
        int numero;
        try {
            numero = Integer.parseInt(parteNumerica);
        } catch (NumberFormatException e) {
            return false;
        }

        // Táboa de letras válidas segundo o cálculo oficial
        String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";

        // Calculamos a letra correcta segundo o número
        char letraCorrecta = letrasValidas.charAt(numero % 23);

        // Comparamos a letra final introducida co resultado esperado
        return letra == letraCorrecta;
    }
}

// ===== Clase Persoa que usa o validador =====
class Persoa {

    private String nif;

    // Constructor
    public Persoa(String nif) {
        // Se non é válido, lanza unha excepción (opcional para estudar)
        if (!NifValidator.validarNif(nif)) {
            throw new IllegalArgumentException("NIF incorrecto: " + nif);
        }

        // Se é válido, gárdase
        this.nif = nif;
    }

    // Getter
    public String getNif() {
        return nif;
    }
}

// ===== Clase principal para probalo todo =====
public class Main {

    public static void main(String[] args) {

        // Probamos un NIF correcto
        System.out.println("Probando NIF '12345678Z': ");
        System.out.println(NifValidator.validarNif("12345678Z")); // true

        // Probamos un NIF incorrecto
        System.out.println("Probando NIF '12345678A': ");
        System.out.println(NifValidator.validarNif("12345678A")); // false

        // Creamos unha persoa válida
        Persoa p = new Persoa("12345678Z");
        System.out.println("Persoa creada co NIF: " + p.getNif());

        // Persoa inválida → lanza excepción
        // Persoa p2 = new Persoa("12345678A"); // Descomenta para probar
    }
}
