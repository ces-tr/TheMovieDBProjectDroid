package com.cestr.themoviedb.model

class CollectionGrouping<K,T> (

    val key: K,
    items: List<T>

) :  MutableList<T> by mutableListOf() {

    init{

        for (item in items) {
            add(item)

        }

    }

}