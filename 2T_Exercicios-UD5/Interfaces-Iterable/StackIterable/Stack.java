/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package stackiterable;

/**
 *
 * @author Usuario
 */
public interface Stack extends Iterable {
    void push(Object o) throws StackException;
    Object pop() throws StackException;
}
