
package encapsulacion.other_package;

import encapsulacion.Employee; // Importamos Employee, porque está noutro package

public class TestAccess { 
    public static void main(String[] args) { 
        Employee e=new Employee("Ana", 2500.0, 30, "IT");

        e.name="X";  //  name é private*
        e.salary=12345.32;  // * salary é protected*, e TestAcces non herda de Employee
        e.age=32;  // * age ten a protección por defecto, pero TestAccess está nun package diferente de Employee*
        e.department="Recursos Humanos";  // public, accesible sen problemas
        System.out.println(e.getName()); // getter público, ok
    }
}

