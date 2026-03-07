/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;

/**
 *
 * @author Usuario
 */
public class ArrayStack implements Stack{
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
    public void push(Object o) throws StackException{
        if(top==data.length){
            throw new StackException("A pila está chea.");
        }
        data[top] = o; // gardar o obxecto na posición top
        top++; // incrementar á seguinte posición

    }
    
    //Desapilar un obxecto (o último elemento apilado).
    //Si está vacío lanzar exception.
    @Override
    public Object pop() throws StackException{
        if(top==0){
            throw new StackException("A pila está valeira.");
        }
        top--; // Apuntamos ó último elemento que estaba arriba.
        Object value = data[top]; // coller o último elemento.
        data[top]=null; // limpiamos o último elemento.
        
        return value; // devolvemos o elemento desapilado.
    }
}
