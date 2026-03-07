
package encapsulacion;

public class Example { 
    public static void main(String[] args) { 
        Person xente=new Person("Xoaquín",21);
        try {
            xente.setAge(-45); 
        } catch(IllegalArgumentException e) {
            System.out.println("Tentaches poñer unha edade errónea.... "+e.getMessage());
        }
        System.out.println(xente);
    }
}

