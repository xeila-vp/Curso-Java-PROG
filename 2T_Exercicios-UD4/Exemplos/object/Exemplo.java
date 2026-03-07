
package object;

public class Exemplo {
    public static void main(String[] args) {
        Persoa p1 = new Persoa("Ana", 30);
        Persoa p2 = new Persoa("Ana", 30);

        System.out.println("toString(): " + p1); // usa toString()
        System.out.println("equals(): " + p1.equals(p2));
        System.out.println("hashCodes: " + p1.hashCode() + " vs " + p2.hashCode());
        System.out.println("Clase real: " + p1.getClass().getName());
    }
}

