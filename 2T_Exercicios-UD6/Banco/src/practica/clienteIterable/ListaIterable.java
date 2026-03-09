package practica.clienteIterable;

import java.util.Iterator;

/**
 * Interface xenérica dunha lista iterable.
 *
 * @param <T> tipo de dato almacenado
 */
public interface ListaIterable<T> extends Iterable<T> {

    void engadir(T dato);

    void eliminar(int index);

    T obter(int index);

    int tamano();

    boolean estaBaleira();

    @Override
    Iterator<T> iterator();
}