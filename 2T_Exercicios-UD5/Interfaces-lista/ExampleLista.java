/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lista;

/**
 *
 * @author Usuario
 */
public class ExampleLista {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //crear array con 3 elmntos
        Lista lista = new ListaConArrays(3);
        
        //engadir os elementos  10, 20, "HOLA", 40, 50, "FIN"
        System.out.println("-- ENGADINDO --");
        
        lista.engadir(10);
        lista.engadir(20);
        lista.engadir("ADIOS");
        lista.engadir(40);
        lista.engadir(50);
        lista.engadir("FIN");

        //eliminar o elementos da posición 2 (adios)
        System.out.println("Elemento a eliminar: " + lista.obter(2));
        
        lista.eliminar(2);
        
        //engadir nesa posición "hola"
        lista.poner(2, "HOLA");

        //mostrar os elementos restantes e o tamaño da lista
        if(lista.estáBaleira()){
            System.out.println("Non hay elementos na lista.");
        }else{
            for (int i = 0; i < lista.tamano(); i++) {
            System.out.printf("Elemento posición: %d -> %s%n", i, lista.obter(i));
            }
            System.out.println("Tamaño actual: " + lista.tamano());
        }
    }
}

