package queue;

public interface IQueue<T> {
    void add(T data);
    T remove();
    T[] toArray(Class<T> clazz);
    int size();
}
