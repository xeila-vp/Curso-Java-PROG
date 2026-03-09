package practica.clientelistArrays;

import java.util.Arrays;

/**
 * Implementación dunha lista con array dinámico.
 *
 * @param <T> tipo de dato almacenado
 */
public class ListaConArrays<T> implements Lista<T> {

    private static final int BUCKET = 3;

    private Object[] data;
    private int size;

    /**
     * Crea a lista coa capacidade inicial indicada.
     *
     * @param capacity capacidade inicial
     */
    public ListaConArrays(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A capacidade debe ser maior que 0.");
        }
        data = new Object[capacity];
        size = 0;
    }

    /**
     * Engade un elemento ao final da lista.
     *
     * @param dato dato a engadir
     */
    @Override
    public void engadir(T dato) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length + BUCKET);
        }

        data[size] = dato;
        size++;
    }

    /**
     * Elimina o elemento da posición indicada.
     *
     * @param index posición a eliminar
     */
    @Override
    public void eliminar(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice non atopado: " + index);
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
    }

    /**
     * Devolve o elemento da posición indicada.
     *
     * @param index posición a consultar
     * @return elemento desa posición
     */
    @Override
    @SuppressWarnings("unchecked")
    public T obter(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice non atopado: " + index);
        }

        return (T) data[index];
    }

    /**
     * Devolve o número real de elementos almacenados.
     *
     * @return tamaño da lista
     */
    @Override
    public int tamano() {
        return size;
    }

    /**
     * Indica se a lista está baleira.
     *
     * @return true se non hai elementos
     */
    @Override
    public boolean estaBaleira() {
        return size == 0;
    }
}