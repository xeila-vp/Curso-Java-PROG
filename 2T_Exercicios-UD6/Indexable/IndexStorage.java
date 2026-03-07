package com.iesrodeira.datastores;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
    3.- Se pide codificar unha clase chamada IndexStorage que ofreza a funcionalidade definida por IIndexStorage<K,V> 
    utilizando a estrutura dinámica da libraría de clases estándar de Java que mellor se axuste.
 */
public class IndexStorage<K,V> implements IIndexStorage<K,V> {
    private Map<K,V> data=new TreeMap<>();
    private Indexable<K,V> indexer;
    private UnaryOperator<V> copier;    // Farei copia defensiva

    public IndexStorage(Indexable<K,V> indexer,UnaryOperator<V> copier) {
        this.indexer=indexer;
        this.copier=copier;
    }
    
    public IndexStorage(Indexable<K,V> indexer) {
        this(indexer,UnaryOperator.identity());  // Sin copia defensiva
    }
    
    @Override
    public V retrieveByKey(K key) {
        return copier.apply(data.get(key));
    }

    @Override
    public V retrieve(V element) {
        if (element==null) return null;
        K key=indexer.index(element);
        return copier.apply(data.get(key));
    }

    @Override
    public boolean add(V element) {
        if (element==null) return false;
        K key=indexer.index(element);
        if (data.containsKey(key)) return false;
        data.put(key, copier.apply(element));
        return true;
    }

    @Override
    public V update(V element) {
        if (element == null) return null;
        K key = indexer.index(element);
        if (!data.containsKey(key)) return null;
        return data.put(key, copier.apply(element));  // Gardamos copia do novo
    }

    @Override
    public V delete(V element) {
        if (element == null) return null;
        return data.remove(indexer.index(element));
    }

    @Override
    public int deleteAll(Predicate<V> selector) {
        Collection<V> lista=data.values();
        int count=0;
        Iterator<V> it=lista.iterator();
        while(it.hasNext()) {
            if (selector.test(it.next())) {
                it.remove();
                count ++;
            }
        }
        return count;
    }

    @Override
    public V[] filter(Predicate<V> filter, Class<V> clazz) {
        List<V> filtered = new ArrayList<>();
        for (V value : data.values()) {
            if (filter.test(value)) {
                filtered.add(copier.apply(value));  // Copia defensiva no resultado
            }
        }
        V[] array = (V[]) Array.newInstance(clazz, filtered.size());
        return filtered.toArray(array);        
    }

    @Override
    public V[] toArray(Class<V> clazz) {
        V[] array = (V[]) Array.newInstance(clazz, data.size());
        int i = 0;
        for (V value : data.values()) {
            array[i++] = copier.apply(value);  // Copia defensiva
        }
        return array;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Iterator<V> iterator() {
        Iterator<V> it = data.values().iterator();  // Iterator subxacente
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();  // Delegamos
            }

            @Override
            public V next() {
                return copier.apply(it.next());  // ✅ Aplicamos copia defensiva
            }

            @Override
            public void remove() {
                it.remove();  // ✅ Delegamos: eliminar do Map subxacente
            }
        };    
    }
    
}
