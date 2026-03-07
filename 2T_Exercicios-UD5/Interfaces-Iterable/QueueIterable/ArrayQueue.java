/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queueiterable;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class ArrayQueue implements Queue {
    //clase que implementa a interface queue cos métodos add e delete.

    private Object[] data; //garda os elementos
    private int front; //Indice do elemento a borrar no inicio da cola.
    private int rear; //indice dónde se mete o seguinte elemento no final da cola.
    private int size; //total de elementos que contén

    public ArrayQueue(int capacity) throws QueueException {
        if (capacity <= 0) {
            throw new QueueException("A capacidade debe ser maior que 0");
        }
        data = new Object[capacity]; //daselle a capacidade ó array (50 elementos neste caso)
        front = 0; // indice do primeiro elemento que existe (posicion do seguinte elemento a borrar)
        rear = 0; // índice da seguinte posición libre onde se engade un elemento
        size = 0; //numero total de elementos actual
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object o) throws QueueException {

        //se a cola está chea...
        if (size == data.length) {
            //throw new QueueException("A cola está chea."); Lanzamos erro
            //ampliamos array
            data=Arrays.copyOf(data, data.length + BUCKET);
        }

        //engadese o elemento na seguinte posición libre.
        data[rear] = o;
        rear++; //avanza a seguinte posición libre.
        size++; //incrementa o contador de elementos.
    }

    @Override
    public Object delete() throws QueueException {
        if (size == 0) {
            throw new QueueException("Non hay elementos que borrar");
        }
        Object elemento = data[front]; //collemos o primeiro elemento da cola
        front++; //avanzamos o inicio da cola
        size--; //reducimos o tamaño do contador
        return elemento;
    }

    @Override
    public Iterator iterator() {
        return new Iterator(){
            private int index = front;
            @Override
            public boolean hasNext() {
                return index < front + size;
            }

            @Override
            public Object next() {
                return data[index++];
            }
        };
    }

}
