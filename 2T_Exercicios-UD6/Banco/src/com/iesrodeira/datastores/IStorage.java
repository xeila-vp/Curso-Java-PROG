package com.iesrodeira.datastores;

public interface IStorage<T> {

    void add(T value);

    T get(int index);

    void set(int index, T value);

    void remove(int index);

    int size();

    T[] values();
}