
package references;

public class Recta {
    private Punto a, b;   // Encapsulación. Evito que se poda acceder os atributos dende fora deste código

    // Construtor: Permite crear unha recta a partir de dous puntos.
    Recta(Punto a, Punto b) {
        this.a = new Punto(a); // Gardo unha COPIA do obxecto, non unha copia da referencia.
        this.b = new Punto(b);
    }

    // Devolve o primeiro punto da recta
    Punto getA() {
        return new Punto(a); // Retorno unha COPIA do atributo, non a referencia do atributo.
    }

    // Devolve o segundo punto da recta
    Punto getB() {
        return new Punto(b); // Retorno unha COPIA do atributo, non a referencia do atributo.
    }


    @Override
    public String toString() {
        return "Recta dende "+a+" a "+b;
    }
}

