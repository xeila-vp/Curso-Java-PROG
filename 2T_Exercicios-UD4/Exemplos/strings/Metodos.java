
package strings;

public class Metodos {
    public static void main(String[] args) {
        String texto = "  Java e Regex  ";

        System.out.println("Lonxitude: " + texto.length());
        System.out.println("Primeiro carácter: " + texto.charAt(0));
        System.out.println("Substring(2,6): " + texto.substring(2,6));
        System.out.println("Maiúsculas: " + texto.toUpperCase());
        System.out.println("Trim: '" + texto.trim() + "'");
        System.out.println("Replace e->E: " + texto.replace('e','E'));
        System.out.println("Equals 'java e regex'? " + texto.trim().equalsIgnoreCase("java e regex"));
    }
}

