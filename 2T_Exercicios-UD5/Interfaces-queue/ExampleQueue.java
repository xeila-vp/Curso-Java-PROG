/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue;

/**
 *
 * @author Usuario
 */
public class ExampleQueue {
    public static void main(String[] args) {
        try {
            Queue queue=new ArrayQueue(50); // Array de 50 elementos.
            
            //engadir
            Object o1 = "Obxecto 1";
            queue.add(o1);
            System.out.println("Engadido: " + o1);
            Object o2 = 12;
            queue.add(o2);
            System.out.println("Engadido: " + o2);
            Object o3 = "Obxecto 3";
            queue.add(o3);
            System.out.println("Engadido: " + o3);
            
            //retirar
            System.out.println("borrado " + queue.delete());
            System.out.println("borrado " + queue.delete());
            System.out.println("borrado " + queue.delete());
            
            //imprimir o total
            System.out.println("Total de elementos: " + queue.size());
            
        } catch(QueueException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
