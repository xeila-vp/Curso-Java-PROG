
package inheritance;

    // Os obxectos da clase *Derived* son tamén obxectos da clase *Base* (extends) 
public class Derived extends Base {
    // Podemos chamar a initY() e initSY() porque é estático e existe antes de crear o obxecto 
    // *NON* Podemos chamar a initX() e initSX() porque son *privados* na clase Base 
    private int y = initY();
    private static int sy = initSY();

    private static int initSY() {
        System.out.println("Clase Derived, método initSY(): inicializando sy");
        return 2;
    }
    private int initY() {
        System.out.println("Clase Derived, método initY(): inicializando y");
        return 20;
    }
    public Derived() {
        System.out.println("---> Construíndo o obxecto Derived: executando constructor Derived");
    }
}

