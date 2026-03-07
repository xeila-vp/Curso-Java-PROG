/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package stack;

/**
 *
 * @author Usuario
 * @param <T>
 * 
 */
public interface Stack<T>{
    void push(T elemento) throws StackException;
    T pop() throws StackException;
    public T[] toArray(Class<T> clazz);
    int size();
}
