package com.iesrodeira.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Creando un ArrayList<T> con copia defensiva e ampliado coa interface IList<T>
 * @author xavi
 * @param <T> 
 */
public class ArrayIList<T> extends ArrayList<T> implements IList<T>{
    private UnaryOperator<T> copier;
    
    
    public ArrayIList(UnaryOperator<T> copier) {
        this.copier=copier;
    }

    public ArrayIList() {
        this(UnaryOperator.identity());
    }
    
    @Override
    public IList<T> process(Function<T, T> processor) {
        ArrayIList<T> newlist=new ArrayIList<>();
        for(T e:this) newlist.add(processor.apply(e));
        newlist.copier=copier;
        return newlist;
    }

    @Override
    public void update(Function<T, T> processor) {
        Iterator<T> iterator=iterator();
        iterator.forEachRemaining(e->processor.apply(e));
    }

    @Override
    public IList<T> filter(Predicate<T> filter) {
        ArrayIList<T> newlist=new ArrayIList<>();
        for(T e:this) if (filter.test(e)) newlist.add(e);
        newlist.copier=copier;
        return newlist;
    }

    
    @Override
    public Object[] toArray() {
        int size=size();
        Object[] array=new Object[size];
        for(int idx=0;idx<size;idx++) array[idx]=get(idx);
        return array;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        int size=size();
        if (ts.length < size)
            ts=(T[])Array.newInstance(ts.getClass().getComponentType(),size);
        for(int idx=0;idx < size;idx++) ts[idx]=(T)get(idx);
        if (size < ts.length) ts[size]=null;
        return ts;
    }

    
    @Override
    public T get(int i) {
        return copier.apply(super.get(i));
    }

    @Override
    public T set(int i, T e) {
        return super.set(i,copier.apply(e));
    }

    @Override
    public void add(int i, T e) {
        super.add(i,copier.apply(e));
    }
    
    @Override
    public boolean add(T e) {
        return super.add(copier.apply(e));
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        boolean ret=false;
        for(T o:clctn) ret=ret || add(o);
        return ret;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> clctn) {
        /*int end=i+size();
        int idx=i;
        for(T o:clctn) {
            add(idx,o);
            idx++;
        }
        return idx!=i;*/
        ArrayList<T> lista=new ArrayList<>();
        for(T o:clctn) lista.add(copier.apply(o));
        return super.addAll(i,lista);
    }


    @Override
    public Iterator<T> iterator() {
        return listIterator();
        /*return new Iterator<>() {
            Iterator<T> listiterator=ArrayIList.super.iterator();
            
            @Override
            public boolean hasNext() {
                return listiterator.hasNext();
            }

            @Override
            public T next() {
                T data=listiterator.next();
                return copier.apply(data);
            }

            @Override
            public void remove() {
                listiterator.remove(); 
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                listiterator.forEachRemaining(action); 
            }

        };*/
    }
    
    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new ListIterator<T>() {
            ListIterator<T> listiterator=ArrayIList.super.listIterator(i);

            @Override
            public boolean hasNext() {
                return listiterator.hasNext();
            }

            @Override
            public T next() {
                return copier.apply(listiterator.next());
            }

            @Override
            public boolean hasPrevious() {
                return listiterator.hasPrevious();
            }

            @Override
            public T previous() {
                return copier.apply(listiterator.previous());
            }

            @Override
            public int nextIndex() {
                return listiterator.nextIndex();
            }

            @Override
            public int previousIndex() {
                return listiterator.previousIndex();
            }

            @Override
            public void remove() {
                listiterator.remove();
            }

            @Override
            public void set(T e) {
                listiterator.set(copier.apply(e));
            }

            @Override
            public void add(T e) {
                listiterator.add(copier.apply(e));
            }
            
        };
    }
}
