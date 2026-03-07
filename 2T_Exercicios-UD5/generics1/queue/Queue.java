/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package queue;

/**
 *
 * @author Usuario
 * @param <T>
 */
public interface Queue<T> { // Interface que define unha cola FIFO
    void add(T element)throws QueueException;  // engade elemento á cola
    T delete()throws QueueException; // retira o primeiro elemento da cola
    
    int size();
    int BUCKET = 10;
}
