/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package stack;

/**
 *
 * @author Usuario
 */
public interface Stack{
    void push(Object o) throws StackException;
    Object pop() throws StackException;
}
