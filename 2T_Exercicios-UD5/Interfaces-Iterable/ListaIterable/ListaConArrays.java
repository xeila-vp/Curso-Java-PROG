/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listaiterable;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class ListaConArrays implements Lista {
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
        System.out.printf("Array con capacidade de: %d%n" , capacity);
    }
    
    //metodos
    
    //engadir e ampliar array si está cheo
    @Override
    public void engadir(Object dato){
        System.out.printf("Elemento %d - %s%n" , size, dato);
        if(size == data.length){
            System.out.printf("Array cheo!%n");
            data=Arrays.copyOf(data, data.length + BUCKET);
            System.out.printf("Array ampliado a %d%n" , data.length);
        }
        data[size]=dato;
        size++;
    }

    @Override
    public void poner(int index, Object dato) {
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
       System.out.printf("Novo dato: %s%n" , dato);
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
    public Object obter(int index) { // Cambiei object polo int do enunciado porque se meten distintos tipos non so int
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Índice non atopado " + index);
        }
        return data[index];
    }

    @Override
    public int tamano() {
        return size;
    }

    @Override
    public boolean estáBaleira() {
        return size==0;//si o tamaño é igual a 0 devolve true.
    }

    @Override
    public Iterator iterator() {
        return new Iterator(){
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public Object next() {
                return data[index++];
            }
        };
    }
}
