
package encapsulacion;

public class Employee { 
    private String name;
    protected double salary;
    int age;  // default, só accesible dende o mesmo paquete
    public String department;

    public Employee(String name, double salary, int age, String department) { 
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.department = department;
    }

    public String getName() { return name; }
    protected double getSalary() { return salary; }
    int getAge() { return age; }  // default getter
    public String getDepartment() { return department; }
}

