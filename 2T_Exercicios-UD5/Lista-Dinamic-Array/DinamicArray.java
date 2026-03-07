package memstore;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Function;

public class DinamicArray<T> implements Iterable<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int CHUNK_GROW=10;

    private Object[] data=null;
    private Copier<T> cloner=null;
    private int size=0;
    
        
    public static <T> DinamicArray<T> copyOf(T[] data, Copier<T> copier) {
        T[] safeCopy = Arrays.copyOf(data, data.length);
        return new DinamicArray<>(safeCopy, copier); 
    }
    
    /**
    * Crea un DinamicArray SEN copia defensiva inicial.
    * Modificar 'data' despois desta chamada implica a modificación do DinamicArray que estamos a crear.
     * @param data
     * @param copier
    */
    public DinamicArray(T[] data,Copier<T> copier) {
        this.data=data;
        this.size=data.length;
        this.cloner=copier;
    }
   
    
    public DinamicArray(Copier<T> copier) {
        this((T[])new Object[INITIAL_CAPACITY],copier);
        this.size=0;
    }
    
    public DinamicArray() {
        this(Copiers.identity());
    }
    
    public DinamicArray(T[] data) {
        this(data,Copiers.identity());
    }


    public void clear() {
        Arrays.fill(data, null);
        size=0;
    }

    public T set(int index, T element) {
        if (index<0 || index >= size) throw new IndexOutOfBoundsException("O indice non existe");
        T old=(T)data[index];
        data[index]=cloner.copy(element);
        return old;
    }
    
    public T get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException("Índice " + idx + " fóra de rango (size=" + size + ")");
        return cloner.copy((T)data[idx]); // Necesito un cast porque data e de tipo Object[]. E seguro porque so permito gardar elementos de tipo T
    }
    
    public boolean add(T element) {
        // Se o array está cheo → ampliámolo
        if (size == data.length) grow();

        data[size] = cloner.copy(element);
        size++;
        return true;
    }

    public void insert(int idx, T element) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException("Índice " + idx + " fóra de rango (size=" + size + ")");
        /*add((T)data[size-1]);
        for(int p=size-1;p >idx; p--) {
            data[p]=data[p-1];
        }
        data[idx]=copy(element);*/
        if (size==data.length) grow();
        System.arraycopy(data,idx,data,idx+1,size-idx);  // Movemos o resto do bloque
        data[idx]=cloner.copy(element);
        size++;
    }
    
    public T remove(int index) {
        if (index<0 || index>=size) throw new IndexOutOfBoundsException("Índice " + index + " fóra de rango (size=" + size + ")");
        T info=(T)data[index];
        System.arraycopy(data,index+1,data,index,size-index-1);
        data[size-1]=null;
        size--;
        return info;
    }
      

    public int length() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T[] process(Function<T,T> processor,Class<? extends T> clazz) {
        // Creamos un array do mesmo tamaño que 'data' usando introspeccion
        T[] newdata=(T[])Array.newInstance(clazz, size);
         
        // Procesamos elemento a elemento
        for (int i = 0; i < size; i++) {
            T elemento=(T)data[i];      // data e un array de Object
            newdata[i] = processor.apply(cloner.copy(elemento));
        }
        return newdata;
    }
    
    public T[] toArray(Class<? extends T> clazz) {
        return process(e->e,clazz);
    }
     
    @SuppressWarnings("unchecked")
    public void update(Function<T,T> processor) {
        // Procesamos elemento a elemento
        for (int i = 0; i < size; i++) {
            T elemento=(T)data[i];      // data e un array de Object
            data[i] = processor.apply(elemento);
        }
    }
    
    @SuppressWarnings("unchecked")
    public DinamicArray<T> filter(Predicate<T> filter) {
        DinamicArray<T> copia=new DinamicArray<>(cloner);
        
        for(int idx=0;idx<size;idx++) {
            if (filter.test((T)data[idx])) {
                copia.add((T)data[idx]);
            }
        }
        return copia;
    }

    @SuppressWarnings("unchecked")
    public Iterator<T> iterator(int from,int to) {
        if (from<0 || from >= to) throw new IllegalArgumentException("Iterator limits");
        return new Iterator<T>() {
            int pos=from;
            int lastpointer=-1;
            
            @Override
            public boolean hasNext() {
                return pos<to;
            }

            @Override
            public T next() {
                if (pos==to) throw new NoSuchElementException();
                T result=(T)data[pos];
                lastpointer=pos;
                pos++;
                return cloner.copy(result);
            }
            
            @Override
            public void remove() {
                if (lastpointer<0) throw new IllegalStateException();
                DinamicArray.this.remove(lastpointer);
                pos=lastpointer;
                lastpointer=-1;
            }
        };
    }
    
    @Override
    public Iterator<T> iterator() {
        return iterator(0,size);
    /*    return new Iterator<T>() {
            int pos=0;
            
            @Override
            public boolean hasNext() {
                return pos<size;
            }

            @Override
            public T next() {
                if (pos==size) throw new NoSuchElementException();
                T result=(T)data[pos];
                pos++;
                return copy(result);
            }
        };
    */    
    }
    

    
    /**
     * grow()
     * ------
     * Método interno que amplía a capacidade do array.
     */
    private void grow() {
        int newCapacity = data.length+CHUNK_GROW;
        data = Arrays.copyOf(data, newCapacity);
    }


}
