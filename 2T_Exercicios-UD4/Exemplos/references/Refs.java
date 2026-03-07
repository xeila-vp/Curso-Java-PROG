
package references;

public class Refs {
    public static void main(String[] args) {

        String a = "hola";
        String b = a;  // b recibe a MESMA referencia que a

        System.out.println(a == b); // true → mesma referencia

        b = "adiós";  // Agora b apunta a OUTRO obxecto distinto

        System.out.println(a);  // segue sendo "hola"; a NON cambia
    }
}

