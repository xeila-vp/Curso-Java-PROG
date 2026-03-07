
package inheritance;

// A clase *Animal* define os atributos e funcionalidades do que consideramos 'e un animal' (un elefante, un can, un tiburón...)
public class Animal {

    // Constructor. Animal será a *superclase* de todos os animais
    public Animal() {
        System.out.println("--> Construíndo un Animal: executando constructor Animal");
    }
    public void speak() {
        System.out.println("--> O Animal dice: ...");
    }
}

