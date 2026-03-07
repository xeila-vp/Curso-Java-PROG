/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lista;

/**
 *
 * @author Usuario
 */
public interface Lista<T>{ // Interface que define unha lista
    void engadir(T dato);//engade un elemento ó final da lista
    void poner(int index, T dato);//pon elemento na posicion indicada
    void eliminar(int index);//elimina o elemento na posicion indicada
    T obter(int index);//devolve o elemento na posición indicada
    int tamano();//devolve o número de elementos almacenados
    boolean estaBaleira(); //Devolve true se a lista non ten elementos.
}
