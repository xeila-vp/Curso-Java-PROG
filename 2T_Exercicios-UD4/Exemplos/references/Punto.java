
package references;

public class Punto {
    private int x;  // Encapsulación. Protexo o acceso ao atributo. So se pode acceder directamente dende este mesmo código.
    private int y;

    Punto(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }

    // *Construtor de copia*: Recibimos un obxecto Punto e creamos un obxecto igual.
    Punto(Punto p) { 
        this.x = p.x; // como x e y son tipos primitivos podemos copiar con =, si foran obxectos, deberíamos gardar unha copia do obxecto.
        this.y = p.y; 
    }

    // Setter: Permite modificar a posición x do punto
    void setX(int x) {
        this.x=x;
    }

    // Setter: Permite modificar a posición y do punto
    void setY(int y) {
        this.y=y;
    }

    // Modifico (sobrepoño) toString herdado de Object para fixar outra representación como String para os obxectos Punto
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}

