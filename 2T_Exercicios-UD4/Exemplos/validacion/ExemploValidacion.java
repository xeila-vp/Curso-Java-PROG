
package validacion;

public class ExemploValidacion {
    public static void main(String[] args) {
        try {
            Persoa p = new Persoa("", -4);  /// provocará excepcións
            System.out.println(p);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validación: " + e.getMessage());
        } finally {
            System.out.println("Finalizando comprobación.");
        }
    }
}

