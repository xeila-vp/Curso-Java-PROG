
package arrays;

public class Declaracion {
    public static void main(String[] args) {
        int[] numeros1; /// Declaración sen inicialización
        numeros1 = new int[5]; /// Inicialización de tamaño 5
        capitais = new String[200]; /// Inicialización de tamaño 200

//*new String[200]* non crea ningún String. Simplemente crea un Array capaz de almacenar 200 referencias a String, que en principio serán *null*

        String[] nomes = {"Ana", "Pedro", "Luis"}; /// Declaración e inicialización combinadas. Crea un array de 3 elementos e garda a referencia a estes 3 String

        System.out.println("Lonxitude de numeros1: " + numeros1.length);
        System.out.println("Lonxitude de capitais: " + capitais.length);
        System.out.println("Lonxitude de nomes: " + nomes.length);
    }
}

