/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

import java.util.Objects;

/**
 *
 * @author Xeila
 */
public class ClasesEObxectos {

    // Clase interna simple para explicar obxectos
    static class Pessoa {

        // Atributos: definen o estado dun obxecto
        private String dni;
        private String nome;
        private String apelidos;

        // Constructor: método especial para crear obxectos
        public Pessoa(String dni, String nome, String apelidos) {
            this.dni = dni;          // gardamos o valor do parámetro no atributo
            this.nome = nome;
            this.apelidos = apelidos;
        }

        // Getter: devolve un atributo
        public String getNome() { 
            return nome; 
        }

        @Override
        public int hashCode() {
            int hash = 7;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Pessoa other = (Pessoa) obj;
            return Objects.equals(this.dni, other.dni);
        }

        @Override
        public String toString() {
            return "Pessoa{" + "dni=" + dni + ", nome=" + nome + ", apelidos=" + apelidos + '}';
        }
        
    }

    public static void demo() {

        // Crear un obxecto usando o constructor
        Pessoa p = new Pessoa("123A", "Ana", "Pérez");

        // Acceder aos seus datos co getter
        System.out.println("Creado obxecto Pessoa: " + p.getNome() + "\n");
    }
}
