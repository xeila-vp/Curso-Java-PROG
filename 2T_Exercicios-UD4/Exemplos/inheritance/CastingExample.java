
package inheritance;

public class CastingExample {
    public static void main(String[] args) {
        Animal a = new Can(); // upcast, seguro
        Can d = (Can) a; // Sabemos que *a* aínda que no seu tipo indica Animal, realmente é un Can. Si queremos usar as funcionalidades de *Can* precisamos un *cast* 
        // *O seguinte é máis seguro:* Podemos verificar previamente ao *cast* que é realmenet un *Can* facendo uso de *instanceof* 
        if (a instanceof Can) {
            System.out.println("É un Can"); 
            Can f = (Can) a;
        }
    }
}

