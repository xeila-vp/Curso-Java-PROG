/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package queueiterable;

/**
 *
 * @author Usuario
 */
public interface Queue extends Iterable { 
    void add(Object o)throws QueueException;  // engade elemento á cola
    Object delete()throws QueueException; // retira o primeiro elemento da cola
    
    int size();
    int BUCKET = 10;
}
