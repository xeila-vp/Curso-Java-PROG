
package strings;

public class Creacion {
    public static void main(String[] args) {
        String s1 = "Hola";  /// Literal
        String s2 = new String("Mundo");  /// Construtor

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);

        String concatenado = s1 + " " + s2;
        System.out.println("Concatenado: " + concatenado);
    }
}

