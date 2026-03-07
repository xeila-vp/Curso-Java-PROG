/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stackiterable;

/**
 *
 * @author Usuario
 */
public class ExampleStack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Stack stack = new ArrayStack(50) {}; // Array tipo Stack (interface)
            
            //apilar obxectos na pila.
            stack.push("Obxecto 1");
            stack.push(12);
            stack.push("Obxecto 3");
            
            for(Object o: stack){
                System.out.println(o);
            }
            //desapilamos os elementos
            System.out.println("Borrado " + stack.pop());
            System.out.println("Borrado " + stack.pop());
            System.out.println("Borrado " + stack.pop());
            
        }catch(StackException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
