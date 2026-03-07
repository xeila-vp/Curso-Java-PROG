package stack;

// Interface que define o comportamento dunha pila (push / pop)
import java.lang.reflect.Array;

// Utilízase para redimensionar arrays de forma cómoda
import java.util.Arrays;

// Excepción que lanzamos cando se intenta sacar un elemento dunha pila baleira
import java.util.NoSuchElementException;

/**
 * Implementación dunha pila (Stack) xenérica usando un array interno.
 *
 * Unha pila segue o principio LIFO:
 * Last In, First Out (o último en entrar é o primeiro en saír).
 *
 * @param <T> tipo de dato almacenado na pila
 */
public class Stack<T> implements IStack<T> {

    /** Tamaño inicial da pila */
    private static final int DEFAULT_SIZE = 20;

    /** Cantidade de espazo que se engade cando a pila se queda pequena */
    private static final int CHUNK_SIZE = 10;
    
    /**
     * Array interno onde se almacenan os elementos da pila.
     * Empregamos Object[] porque Java non permite crear arrays de tipos xenéricos.
     */
    private Object[] stack;

    /**
     * Índice do seguinte espazo libre da pila.
     *
     * - Indica cantos elementos hai realmente na pila
     * - O último elemento válido está en stack[top - 1]
     */
    private int top = 0;
    
    /**
     * Construtor por defecto.
     * Crea a pila cun tamaño inicial fixo.
     */
    public Stack() {
        stack = new Object[DEFAULT_SIZE];
    }
    
    /**
     * Engade un elemento á pila.
     * O elemento colócase no "tope" da pila.
     *
     * @param data elemento a engadir
     */
    @Override
    public void push(T data) {

        // Se o array está cheo, necesitamos máis espazo
        if (top == stack.length) {
            // Creamos un novo array máis grande e copiamos os datos antigos
            stack = Arrays.copyOf(stack, stack.length + CHUNK_SIZE);
        }

        // Gardamos o elemento na posición libre
        stack[top] = data;

        // Actualizamos o índice do tope
        top++;
    }

    /**
     * Extrae e devolve o último elemento engadido á pila.
     *
     * @return elemento do tope da pila
     * @throws NoSuchElementException se a pila está baleira
     */
    @SuppressWarnings("unchecked")
    @Override
    public T pop() {

        // Se non hai elementos, non se pode facer pop
        if (top == 0)
            throw new NoSuchElementException("O Stack está baleiro");

        // Reducimos o tope para apuntar ao último elemento válido
        top--;

        // Recuperamos o valor almacenado
        T value = (T) stack[top];

        // Eliminamos a referencia para permitir ao Garbage Collector liberar memoria
        stack[top] = null;

        // Devolvemos o elemento extraído
        return value;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> clazz) {
        T[] result = (T[]) Array.newInstance(clazz, top);
        System.arraycopy(stack, 0,result,0,top);
        return result;    
    }
    
    @Override
    public int size() {
        return top;
    }
}