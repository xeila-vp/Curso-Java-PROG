
package inheritance;

public class OverloadingExample {
    public void m(Number n) { System.out.println("Versión con arggumento de tipo Number"); }
    public void m(Integer i) { System.out.println("Versión con arggumento de tipo Integer"); } // sobrecarga: distintos parámetros

    public static void main(String[] args) {
        OverloadingExample ex = new OverloadingExample();
        Number num = 5; // Do mesmo xeito que con String, esta constante se convirte a Number de xeito automático.
        ex.m(num); // chama *m(Number)* en tempo de compilación
        ex.m((Integer)num); // chama *m(Integer)*
    }
}

