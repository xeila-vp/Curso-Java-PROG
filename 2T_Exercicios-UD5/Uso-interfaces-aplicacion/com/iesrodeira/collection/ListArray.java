package com.iesrodeira.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.ConcurrentModificationException;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Implementación de List<T> e IList<T> usando DinamicArray como backend.
 * @author xavi
 * @param <T> 
 */
public class ListArray<T> implements IList<T> {
    private DinamicArray<T> array;
    private UnaryOperator<T> copier;
    private int modCount=0;
        
    public ListArray(UnaryOperator<T> copier) {
        array=new DinamicArray<>(copier);
        this.copier=copier;
    }
    
    public ListArray() {
        this(UnaryOperator.identity());
    }
        
    public int modCount() {
        return modCount;
    }
    
    @Override
    public boolean add(T e) {
        modCount++;
        return array.add(e);
    }
    
    @Override
    public void add(int i, T e) {
        modCount++;
        if (i==size()) array.add(e);
        else array.insert(i, e);
    }
    
    @Override
    public boolean addAll(Collection<? extends T> clctn) {
       if (clctn==null) throw new NullPointerException("In ListArray.addAll");
       if (clctn.isEmpty()) return false;
       for(T o:clctn) {
           add(o);
        }
        return true;
    }
    
    @Override
    public boolean addAll(int i, Collection<? extends T> clctn) {
        if (clctn==null) throw new NullPointerException("In ListArray.addAll");
        if (clctn.isEmpty()) return false;
        int pos=i;
        for(T o:clctn) {
            add(pos,o);
            pos++;
        }
        return true;
    }
    
    @Override
    public void clear() {
        modCount++;
        array.clear();
    }
    
    @Override
    public boolean contains(Object o) {
        for(Object element:array) {
            if (Objects.equals(element,o)) return true;
        }
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> clctn) {
        if (clctn==null) throw new NullPointerException("In ListArray.containsAll");
        for(Object o:clctn) {
            if (!contains(o)) return false;
        }
        return true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof List)) {
            return false;
        }
        final List<?> other = (List<?>) obj;
        if (other.size()!=size()) return false;
        Iterator<?> it=other.iterator();
        for(T el:this) {
            if (!Objects.equals(it.next(), el)) return false;
        }
        return true;
    }
    
    @Override
    public T get(int i) {
        return array.get(i);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        for (T e : array) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
    
    private int indexOf(Object o,int from,int to, int inc) {
        for(int idx=from;idx!=to;idx+=inc) {
            Object el=array.get(idx);
            if (Objects.equals(el, o)) return idx;
        }
        return -1;
    }

    @Override
    public int indexOf(Object o) {
        return indexOf(o,0,size(),1);
    }
    
    @Override
    public int lastIndexOf(Object o) {
        return indexOf(o,size()-1,-1,-1);
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public ListIterator<T> listIterator(int i) {
        if (i<0 || i>size()) throw new IndexOutOfBoundsException();
        
        return new ListIterator<T> () {
            int idx=i;
            int last=-1;
            int myCount=modCount;
        
            private boolean isModified() {
                return modCount()!=myCount;
            }
            
            @Override
            public boolean hasNext() {
                if (isModified()) throw new ConcurrentModificationException();
                return (idx<size());
            }

            @Override
            public T next() {
                if (isModified()) throw new ConcurrentModificationException();
                if (idx>=size()) throw new NoSuchElementException("In listIterator");
                last=idx;
                idx++;
                return array.get(last);
            }

            @Override
            public boolean hasPrevious() {
                if (isModified()) throw new ConcurrentModificationException();
                return idx>0;
            }

            @Override
            public T previous() {
                if (isModified()) throw new ConcurrentModificationException();
                if (idx<=0) throw new NoSuchElementException("In listIterator");
                idx--;
                last=idx;
                return array.get(idx);
            }

            @Override
            public int nextIndex() {
                if (isModified()) throw new ConcurrentModificationException();
                return idx;
            }

            @Override
            public int previousIndex() {
                if (isModified()) throw new ConcurrentModificationException();
                return idx-1;
            }

            @Override
            public void remove() {
                if (isModified()) throw new ConcurrentModificationException();
                if (last < 0) throw new IllegalStateException();
                ListArray.this.remove(last);
                if (idx > last) idx--;
                last=-1;
                myCount++;
            }

            @Override
            public void set(T e) {
                if (isModified()) throw new ConcurrentModificationException();
                if (last < 0) throw new IllegalStateException();
                array.set(last,e);
                //last=-1;
            }

            @Override
            public void add(T e) {
                if (isModified()) throw new ConcurrentModificationException();
                ListArray.this.add(idx,e);
                //if (idx == size()) array.add(e);
                //else               array.insert(idx,e);
                idx++;
                last=-1;
                myCount++;
            }
        };
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }
    
    @Override
    public Iterator<T> iterator() {
        return listIterator(0);
    }
        
    public T remove(int i) {
        modCount++;
        return array.remove(i);
    }
    
    @Override
    public boolean remove(Object o) {
        int idx=indexOf(o);
        if (idx>=0) {
            remove(idx);
            return true;
        }
        return false;
    }
     
    @Override
    public boolean removeAll(Collection<?> clctn) {
        if (clctn==null) throw new NullPointerException("In ListArray.removeAll");
        boolean removed = false;
        Iterator<T> it=iterator();
        while(it.hasNext()) {
            if (clctn.contains(it.next())) {
                it.remove();
                removed=true;
            }
        }
        return removed;
    }
    
    @Override
    public boolean retainAll(Collection<?> clctn) {
        if (clctn==null) throw new NullPointerException("In ListArray.retainAll");
        boolean ret=false;
        if (clctn.isEmpty()) {
            if (!isEmpty()) {
                clear();
                ret=true;
            }
        } else {
            Iterator<T> it=iterator();
            while(it.hasNext()) {
                if (!clctn.contains(it.next())) {
                    it.remove();
                    ret=true;
                }
            }
        }
        return ret;
    }
        
    @Override
    public T set(int i, T e) {
        return array.set(i, e);
    }
    
    @Override
    public int size() {
        return array.length();
    }

    @Override
    public List<T> subList(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public Object[] toArray() {
        int length=size();
        Object[] result=new Object[length];
        for(int idx=0;idx<length;idx++) result[idx]=array.get(idx);
        return result;
    }

    @Override
    public <U> U[] toArray(U[] ts) {
        int length=size();
        if (ts.length < length)
            return (U[]) Arrays.copyOf(toArray(), length, ts.getClass());
        System.arraycopy(toArray(), 0, ts, 0, length);
        if (ts.length > length)  ts[length] = null;
        return ts;      
    }

    @Override
    public IList<T> process(Function<T,T> processor) {
        ListArray<T> copia=new ListArray<>(copier);
        for(T data:this) copia.add(data);
        return copia;
    }

    @Override
    public void update(Function<T,T> processor) {
        array.update(processor);
    }

    @Override
    public IList<T> filter(Predicate<T> filter) {
        ListArray<T> copia=new ListArray<>(copier);
        for(T data:this) if (filter.test(data)) copia.add(data);
        return copia;        
    }
    
}
