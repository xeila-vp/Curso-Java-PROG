package memstore;

@FunctionalInterface
public interface Copier<T> {
    T copy(T data);
}
