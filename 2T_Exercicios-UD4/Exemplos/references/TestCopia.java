
package references;

public class TestCopia {
    public static void main(String[] args) {

// Imos ver como utilizar o construtor de copia para facer copias independentes, en lugar de copiar so a referencia como antes

        Punto p1 = new Punto(10, 20);
        Punto p2 = new Punto(p1);  // Creamos un novo obxecto Punto cos mesmos valores nos atributos x e y que p1

        p2.setX(999);  // Isto *NON modifica p1, porque son obxectos distintos*
        System.out.println(p1);  
    }
}

