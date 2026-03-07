/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package listaiterable;

/**
 *
 * @author Usuario
 */
public interface Lista extends Iterable{ // Interface que define unha lista
    void engadir(Object dato);//engade un elemento ó final da lista
    void poner(int index, Object dato);//pon elemento na posicion indicada
    void eliminar(int index);//elimina o elemento na posicion indicada
    Object obter(int index);//devolve o elemento na posición indicada
    int tamano();//devolve o número de elementos almacenados
    boolean estáBaleira(); //Devolve true se a lista non ten elementos.
}
