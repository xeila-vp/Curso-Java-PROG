package com.iesrodeira.datastores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IndexStorage<K, V> implements IIndexStorage<K, V> {

    private Map<K, V> data;

    public IndexStorage() {
        data = new HashMap<>();
    }

    @Override
    public void add(K key, V value) {
        if (data.containsKey(key)) {
            throw new IllegalArgumentException("Clave duplicada");
        }
        data.put(key, value);
    }

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public void set(K key, V value) {
        if (!data.containsKey(key)) {
            throw new IllegalArgumentException("Clave non atopada");
        }
        data.put(key, value);
    }

    @Override
    public void remove(K key) {
        if (!data.containsKey(key)) {
            throw new IllegalArgumentException("Clave non atopada");
        }
        data.remove(key);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        Collection<V> values = data.values();
        return (V[]) values.toArray(new Object[0]);
    }
}
