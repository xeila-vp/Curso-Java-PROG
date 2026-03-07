package com.iesrodeira.storage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class StorageList<T> implements IStorage<T> {
    private List<T> list;
   
    
    public StorageList(List<T> list) {
        this.list=list;
    }
        
    @Override
    public T retrieve(T element) {
        int idx=list.indexOf(element);
        if (idx>=0) return list.get(idx);
        return null;
    }

    @Override
    public boolean add(T element) {
        if (element==null) return false;
        if (list.indexOf(element) >= 0) return false;
        
        list.add(element);
        return true;
    }

    @Override
    public T update(T data) {
        if (data==null) return null;
        int idx=list.indexOf(data);
        if (idx < 0) return null;
        T old=list.get(idx);
        list.set(idx,data);
        return old;
    }

    @Override
    public T delete(T data) {
        int idx=list.indexOf(data);
        if (idx < 0) return null;
        T old=list.get(idx);
        list.remove(idx);
        return old;
    }

    @Override
    public int deleteAll(Predicate<T> selector) {
        List<T> todelete=new ArrayList<T>();
        for(T elem:list) {
            if (selector.test(elem)) todelete.add(elem);
        }
        int count=todelete.size();
        list.removeAll(todelete);
        return count;
    }

    @Override
    public T[] filter(Predicate<T> filter, Class<T> clazz) {
        List<T> filtered=new ArrayList<T>();
        for(T elem:list) {
            if (filter.test(elem)) filtered.add(elem);
        }
        return filtered.toArray(n->(T[])Array.newInstance(clazz,n));
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        return list.toArray(n->(T[])Array.newInstance(clazz, n));
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
