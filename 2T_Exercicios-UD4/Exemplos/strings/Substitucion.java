
package strings;

public class Substitucion {
    public static void main(String[] args) {
        String texto = "O número 123 está aquí";
        String novoTexto = texto.replaceAll("\\d+", "XXX");
        System.out.println("Antes: " + texto);
        System.out.println("Despois: " + novoTexto);
    }
}

