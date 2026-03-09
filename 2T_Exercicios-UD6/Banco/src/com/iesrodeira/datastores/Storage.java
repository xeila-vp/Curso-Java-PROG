package com.iesrodeira.datastores;

import java.util.Arrays;

public class Storage<T> implements IStorage<T> {

    private static final int BUCKET = 5;
    private Object[] data;
    private int size;

    public Storage() {
        data = new Object[BUCKET];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length + BUCKET);
        }
        data[size] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice non válido: " + index);
        }
        return (T) data[index];
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice non válido: " + index);
        }
        data[index] = value;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice non válido: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] values() {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = (T) data[i];
        }
        return result;
    }
}