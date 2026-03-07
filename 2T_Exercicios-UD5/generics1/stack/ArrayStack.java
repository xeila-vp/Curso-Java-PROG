/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;

import java.util.Arrays;

/**
 *
 * @author Usuario
 * @param <T>
 * 
 */
public class ArrayStack<T> implements Stack<T>{
//esta clase implementa os métodos da interface Stack (push e pop).
        
    private Object[] data; //array cos elementos da pila.
    private int top; // lonxitude do array = num de elementos na pila.
    
    
    //CONSTRUCTOR DA CLASE
    public ArrayStack(int size){
        //recibe o tamaño máximo.
        data = new Object[size]; //crear array co tamaño indicado.
        top = 0; //inicializar a pila como vacía.

    }
    
    //método para apilar un obxecto na fila na posición correspondente. 
    //si está cheo lanza exception.
    @Override
    public void push(T elemento) throws StackException{
        if(top==data.length){
            throw new StackException("A pila está chea.");
        }
        data[top] = elemento; // gardar o obxecto na posición top
        top++; // incrementar á seguinte posición
    }
    
    //Desapilar un obxecto (o último elemento apilado).
    //Si está vacío lanzar exception.
    @Override
    public T pop() throws StackException{
        if(top==0){
            throw new StackException("A pila está valeira.");
        }
        top--; // Apuntamos ó último elemento que estaba arriba.
        T value = (T) data[top]; // coller o último elemento.
        
        return value; // devolvemos o elemento desapilado.
    }   
    
    @Override
    public T[] toArray(Class<T> clazz) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, top);
        for (int i = 0; i < top; i++) {
            array[i] = (T) data[i];
        }
    return array;
    }
                                                                                                                                                                                                       
    // Retornamos o número de elementos almacenados, que é o valor de top                                                                                                                                         
    @Override                                                                                                                                                                                                     
    public int size() {                                                                                                                                                                                           
        return top;                                                                                                                                                                                               
    }   

}
