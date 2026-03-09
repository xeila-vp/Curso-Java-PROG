package practica.clientelistArrays;

/**
 * Interface xenérica dunha lista simple.
 *
 * @param <T> tipo de dato almacenado
 */
public interface Lista<T> {

    void engadir(T dato);

    void eliminar(int index);

    T obter(int index);

    int tamano();

    boolean estaBaleira();
}
