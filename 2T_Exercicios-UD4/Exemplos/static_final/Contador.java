
package static_final;

/// Clase que usa static e final nun contexto realista
public class Contador {

    /// Constante global da clase (inmutable, compartida)
    public static final String PREFIX = "CTR-";

    /// Atributo compartido entre todas as instancias
    private static int total = 0;

    /// Atributo final: a referencia non pode cambiar
    private final String id;

    public Contador() {
        total++;
        this.id = PREFIX + total;
    }

    /// Método static: non precisa obxecto para chamarse
    public static int getTotal() {
        return total;
    }

    /// Método NON static: require unha instancia
    public String getId() {
        return id;
    }
}

