
package static_final;

public class Proba {
    public static void main(String[] args) {

        System.out.println("Total inicial: " + Contador.getTotal());

        Contador c1 = new Contador();
        Contador c2 = new Contador();
        Contador c3 = new Contador();

        System.out.println("Total tras crear obxectos: " + Contador.getTotal());
        System.out.println("IDs xerados:");
        System.out.println(c1.getId());
        System.out.println(c2.getId());
        System.out.println(c3.getId());
    }
}

