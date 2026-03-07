
package encapsulacion;

public class Person { 
    private String name;  // Agora son PRIVADOS, so se pode acceder dende o codigo escrito nesta clase.
    private int age;

    // Si se intenta crear unha Person cunha edade ou nome erroneos o notifico cunha Exception.
    public Person(String name, int age) throws IllegalArgumentException {  
        if ((name==null) || (name.isEmpty())) throw new IllegalArgumentException("Debe especificarse un nome");
        if (age<=0) throw new IllegalArgumentException("A edade debe ser > 0");
        this.name = name;
        this.age = age;
    }

    // So se pode acceder ao nome mediante este método.
    public String getName() { 
        return name; 
    }

    // So se pode acceder a edade mediante este método.
    public int getAge() { 
        return age; 
    }

    // Este método e o unico modo de modificar a edade. Podemos introducir un control para evitar edades erróneas.
    public void setAge(int newage) throws IllegalArgumentException{ 
        if (newage<=0) throw new IllegalArgumentException("A edade debe ser > 0");
        this.age=newage; 
    }

    @Override
    public String toString() { 
        return name+" ("+this.age+" anos)"; 
    }
}

