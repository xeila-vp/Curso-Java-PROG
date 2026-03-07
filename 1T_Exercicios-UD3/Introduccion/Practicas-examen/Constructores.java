/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class Constructores {

    // Clase exemplo para explicar constructores
    static class Vehiculo {
        private String marca;
        private int ano;

        // Constructor: sempre ten o mesmo nome ca clase
        public Vehiculo(String marca, int ano) {
            this.marca = marca; // “this” diferencia atributo de parámetro
            this.ano = ano;
        }

        public String getMarca() { 
            return marca; 
        }
    }

    public static void demo() {
        // Crear obxecto Vehiculo co constructor
        Vehiculo v = new Vehiculo("Toyota", 2020);

        System.out.println("Vehiculo marca: " + v.getMarca() + "\n");
    }
}
