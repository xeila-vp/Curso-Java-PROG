package queue;

// Interface que define o comportamento dunha cola (add / delete)
import java.lang.reflect.Array;

// Utilízase para redimensionar e copiar arrays

// Excepción que se lanza cando se intenta borrar dunha cola baleira
import java.util.NoSuchElementException;

/**
 * Implementación dunha cola (Queue) xenérica baseada nun array.
 *
 * Unha cola segue o principio FIFO:
 * First In, First Out (o primeiro en entrar é o primeiro en saír).
 *
 * @param <T> tipo de dato almacenado na cola
 */
public class Queue<T> implements IQueue<T> {
    /**
     * Array interno onde se almacenan os elementos da cola.
     * Usamos Object[] porque Java non permite arrays de tipos xenéricos.
     */
    private Object[] queue;
    private boolean empty;

    /**
     * Índice do primeiro elemento válido da cola.
     * Apunta ao elemento que se devolverá no seguinte delete().
     */
    private int front = 0;

    /**
     * Índice da seguinte posición libre da cola.
     * Os novos elementos engádense aquí.
     */
    private int end = 0;
    
    /**
     * Construtor por defecto.
     * Crea a cola cun tamaño inicial fixo.
     */
    public Queue(int size) {
        queue = new Object[size];
        empty = true;
    }
    
    /**
     * Engade un elemento ao final da cola.
     *
     * @param data elemento a engadir
     */
    @Override
    public void add(T data) {
        if (front==end && !empty) throw new IllegalStateException("A cola está chea");

        // Gardamos o elemento na posición final
        queue[end] = data;

        // Avanzamos o índice de fin
        end++;
        if (end==queue.length) end=0;
        empty=false;
    }

    /**
     * Elimina e devolve o primeiro elemento da cola.
     *
     * @return elemento eliminado
     * @throws NoSuchElementException se a cola está baleira
     */
    @Override
    @SuppressWarnings("unchecked")
    
    public T remove() {

        // Se front e end coinciden, non hai elementos
        if (empty)
            throw new NoSuchElementException("Queue sen elementos");

        // Recuperamos o elemento do inicio da cola
        T data = (T) queue[front];
        queue[front]=null;
        front++;
        if (front==queue.length) front=0;
        if (front==end) empty=true;

        // Devolvemos o elemento eliminado
        return data;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(Class<T> clazz) {
        int size=size();
        T[] result = (T[]) Array.newInstance(clazz, size);
        if (empty) return result;
        if (front >= end) {
            System.arraycopy(queue, front,result,0,queue.length-front);
            System.arraycopy(queue, 0,result,queue.length-front,end);
        } else {
            System.arraycopy(queue, front,result,0,size);
        }
        return result;
    }
    
    @Override
    public int size() {
        if (empty) return 0;
        if (front == end) return queue.length;

        if (front > end)
            return queue.length - front + end;
        else
            return end - front;
    }
}

