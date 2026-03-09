package com.iesrodeira.datastores;

public interface IIndexStorage<K, V> {

    void add(K key, V value);

    V get(K key);

    void set(K key, V value);

    void remove(K key);

    int size();

    V[] values();
}
