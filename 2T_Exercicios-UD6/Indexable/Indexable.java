package com.iesrodeira.datastores;
/*
1.- Un obxecto da clase V considérase indexable se é capaz de proporcionar unha chave única da clase K que o identifica.
    Pídese deseñar a estrutura necesaria en Java para modelar este comportamento no package com.iesrodeira.datastores
*/
public interface Indexable<K,V> {
    K index(V data);
}

  
