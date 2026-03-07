
package validacion;

public class ExemploPersoaSegura {
    public static void main(String[] args) {
        PersoaSegura p = new PersoaSegura();
        try {
            p.setIdade(150);  /// provocará excepción personalizada
            System.out.println("Idade establecida: " + 150);
        } catch (IdadeIncorrectaException e) {
            System.out.println("Erro detectado: " + e.getMessage());
        } finally {
            System.out.println("Comprobación finalizada.");
        }
    }
}

