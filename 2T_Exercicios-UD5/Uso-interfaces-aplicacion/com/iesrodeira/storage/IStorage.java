package com.iesrodeira.storage;

import java.util.function.Predicate;


public interface IStorage<T> extends Iterable<T>{
    public T retrieve(T element);
    public boolean add(T element);
    public T update(T data);
    public T delete(T data);
    public int deleteAll(Predicate<T> selector);
    T[] filter(Predicate<T> filter, Class<T> clazz);
    T[] toArray(Class<T> clazz);
    public int size();
    public void clear();
    
}
