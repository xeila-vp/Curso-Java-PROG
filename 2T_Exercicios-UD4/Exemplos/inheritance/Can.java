
package inheritance;

// A clase *Can* define os atributos e funcionalidades do que consideramos 'e un Can' 
public class Can extends Animal {  // Para empezar, *un Can é un Animal* (extends). Dese xeito un Can ten a capacidade *speak()* 
    // Primeiro chamará automáticamente super() se non se indica outro
    public Can() {
        System.out.println("-->Construindo un Can: executando construtor Can");
    }

    // Esta clase está sobrepoñendo o método *speak()* definido en Animal. Se debe indicar coa anotación *@Override*
    @Override
    public void speak() {
        System.out.println("--> O Can dice: guau, guau");
    }
}

