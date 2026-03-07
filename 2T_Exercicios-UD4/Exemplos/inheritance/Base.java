
package inheritance;

public class Base {
    // Podemos chamar a initX() e initSX() porque é estático e existe antes de crear o obxecto 
    private int x = initX(); // Inicializamos o atributo. Isto se leva acabo antes da execución do construtor da clase.
    private static int sx = initSX();

    private static int initSX() { // Este método é privado. So se pode chamar desde este mesmo código na propia clase. Tamén é estático, pertence a clase. Existe fora dos obxectos 
        System.out.println("Clase Base, método iniSX(): inicializando sx");
        return 1;
    }
    private int initX() {    // Este método é privado. So se pode chamar desde este mesmo código na propia clase. Tamén é estático, pertence a clase. Existe fora dos obxectos 
        System.out.println("Clase Base, método initX(): inicializando x");
        return 10;
    }
    public Base() {
        System.out.println("---> Construindo o obxecto Base: executando constructor Base");
    }
}

