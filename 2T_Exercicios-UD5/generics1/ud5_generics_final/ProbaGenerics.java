/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ud5_generics_final;

import stack.Stack;
import stack.ArrayStack;
import lista.Lista;
import lista.ListaConArrays;
import queue.ArrayQueue;
import queue.Queue;
import queue.QueueException;
import stack.StackException;

/**
 *
 * @author Usuario
 */
public class ProbaGenerics {

    /**
     * @param args the command line arguments
     * @throws queue.QueueException
     * @throws stack.StackException
     */
    public static void main(String[] args) throws QueueException, StackException {
        Lista<Double> listaPrecio = new ListaConArrays<>(10);
        Lista<String> listaCousas = new ListaConArrays<>(10);
        Queue<Integer> cola = new ArrayQueue<>(10);
        Queue<Double> outraCola = new ArrayQueue<>(10);
        Stack<String> pilaNomes = new ArrayStack<>(5);
        Stack<Integer> pilaNumeros = new ArrayStack<>(2);
        
        listaPrecio.engadir(20.5);
        listaPrecio.engadir(5.5);
        listaCousas.engadir("Coche");
        listaCousas.engadir("Casa");
        
        if(!listaCousas.estaBaleira()){
            System.out.print("Lista de cousas: ");
            for(int i=0;i<listaCousas.tamano();i++){
                System.out.print("[" + listaCousas.obter(i)+ "]");
            }System.out.printf("%n");
        }
        if(!listaPrecio.estaBaleira()){
            System.out.print("Lista de Prezos: ");
            for(int i=0;i<listaPrecio.tamano();i++){
                System.out.print("[" + listaPrecio.obter(i)+ "]");
            }System.out.print("");
        }
        
        System.out.println("--------------");
        System.out.println("Prezos na lista: "+ listaPrecio.tamano());
        System.out.println("Cousas na lista: "+ listaPrecio.tamano());
        
        System.out.println("----- Borrar a lista de prezos: ----");
        while(!listaPrecio.estaBaleira()){
            for(int i=0;i<listaPrecio.tamano();i++){
                System.out.println("Borrado: " + listaPrecio.obter(i));
                listaPrecio.eliminar(i);
                break;
            }
        }System.out.println("Non quedan prezos na lista.");
        
        System.out.println("------ Engadir á cola ------");

        int num1=50;
        cola.add(num1);
        System.out.println("Engadido: " + num1);
        outraCola.add(55.2);
        
        System.out.println("------ Engadir á pila ------");
        
        pilaNomes.push("Juana");
        pilaNomes.push("Ramón");
        pilaNumeros.push(200);
        
        String[] datos = pilaNomes.toArray(String.class);
        
        System.out.print("Pila de nomes: ");
        for(String s : datos){
            System.out.print("[" + s + "]");
        }
        System.out.println();
        
        System.out.println("Borrar en Pila de nomes: " + pilaNomes.pop());
        System.out.println("Borrar en Pila de números: " + pilaNumeros.pop());
        System.out.println("Elementos da cola: " + cola.size());
        
    }
}
