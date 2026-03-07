package stack;

public interface IStack<T> {
    void push(T data);
    T pop();    
    T[] toArray(Class<T> clazz);
    int size();
}
