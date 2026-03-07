package com.iesrodeira.datastores;



      import java.util.function.Predicate;

      public interface IStorage<T>  extends Iterable<T>{
          public T retrieve(T element);      // Localiza no IStorage o elemento "element", retorna null se non existe{BLUE}
          public boolean add(T element);     // Engade un *novo* elemento. Retorna false se o elemento xa existe ou non se consigue engadir.{BLUE}
          public T update(T data);           // Actualiza *un elemento existente*. Retorna o elemento antiguo ou null se non se actualizou.{BLUE}
          public T delete(T data);           // Elimina un *elemmento existente*, retorna o elemento antiguo ou null se non se eliminou nada{BLUE}
          public int deleteAll(Predicate<T> selector);   // Elimina os elementos seleccionados polo Predicate{BLUE}
          T[] filter(Predicate<T> filter, Class<T> clazz);  // Retorna un array con todos os elementos seleccionados polo Predicate{BLUE}
          T[] toArray(Class<T> clazz);   // Retorna un array con todos os elementos{BLUE}
          public int size();  // Retorna o número de elementos gardados no IStorage{BLUE}
          public void clear();  // Elimina todos os elementos do IStorage{BLUE}
      }
