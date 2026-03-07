/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lista;

import java.util.Arrays;

/**
 *
 * @author Usuario
 * @param <T>
 */
public class ListaConArrays<T> implements Lista<T> {
    //clase que implementa a interface Lista cos métodos indicados.
    
    private Object[] data; //garda os elementos
    private int size; //num de elementos na lista
    public static final int BUCKET = 3; //ampliación do array
    
    //constructor que inicia o array coa capacidade determinada
    public ListaConArrays(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("A capacidade debe ser maior que 0.");
        }
        data = new Object[capacity];
    }
    
    //metodos
    
    //engadir e ampliar array si está cheo
    @Override
    public void engadir(T dato){
        if(size == data.length){
            data=Arrays.copyOf(data, data.length + BUCKET);
        }
        data[size]=dato;
        size++;
    }

    @Override
    public void poner(int index, T dato) {
       if(index < 0 || index > size){ //si index é menor que a primeira pos e maior que a última, non é válido e lanza erro
           throw new IndexOutOfBoundsException("Índice non atopado." + index);
       }
       if(size == data.length){ //si o array está cheo, amplíase.
           data = Arrays.copyOf(data, data.length + BUCKET);
       }
       //dende o ultimo elemento da lista, desprázase ata a posicion seguinte do index indicado.
       for(int i = size; i > index; i--){
           data[i] = data[i-1];
       }
       data[index]=dato;
       size++;
    }

    @Override
    public void eliminar(int index) {
        //comprobamos indice válido
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Índice non atopado." + index);
        }
        //desprazamos á esquerda para non deixar ocos
        for(int i = index; i < size-1; i++){
            data[i]=data[i+1];
        }
        //reducimos o tamaño
        size--;
    }

    @Override
    public T obter(int index) { // Cambiei object polo int do enunciado porque se meten distintos tipos non so int
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Índice non atopado " + index);
        }
        return (T) data[index];
    }

    @Override
    public int tamano() {
        return size;
    }

    @Override
    public boolean estaBaleira() {
        return size==0;//si o tamaño é igual a 0 devolve true.
    }
}
