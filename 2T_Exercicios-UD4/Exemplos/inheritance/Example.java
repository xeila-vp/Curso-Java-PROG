
package inheritance;

// Este é o exemplo de demostración. 
public class Example {
    public static void main(String[] args) {
        System.out.println("Creando un Animal...");
        Animal a = new Animal();
        System.out.println();
        System.out.println("Creando un Can...");
        Animal d = new Can(); // Un Can *é un* Animal, polo tanto, podemos gardar a suúa referencia nunha variable de tipo Animal 
        System.out.println("---");
        a.speak(); // Facemos que o animal referenciado por *a*, fale (chamamos ao método speak()) 
        d.speak(); // Facemos que o animal referenciado por *b*, fale (chamamos ao método speak()) 
        //  *Podemos observar que b, dado que e un Can, 'fala' como un Can. Iso é POLIMORFISMO*. 
    }
}

