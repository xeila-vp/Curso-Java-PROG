package com.iesrodeira.datastores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IndexStorage<K, V> {

    private Map<K, V> data;

    public IndexStorage() {
        data = new HashMap<>();
    }

    public void add(K key, V value) {
        if (data.containsKey(key)) {
            throw new IllegalArgumentException("Clave duplicada");
        }
        data.put(key, value);
    }

    public V get(K key) {
        return data.get(key);
    }

    public void set(K key, V value) {
        if (!data.containsKey(key)) {
            throw new IllegalArgumentException("Clave non atopada");
        }
        data.put(key, value);
    }

    public void remove(K key) {
        if (!data.containsKey(key)) {
            throw new IllegalArgumentException("Clave non atopada");
        }
        data.remove(key);
    }

    public int size() {
        return data.size();
    }

    @SuppressWarnings("unchecked")
    public V[] values() {
        Collection<V> values = data.values();
        return (V[]) values.toArray(new Object[0]);
    }
}