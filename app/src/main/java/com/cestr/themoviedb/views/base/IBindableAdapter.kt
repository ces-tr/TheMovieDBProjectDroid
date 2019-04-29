package com.cestr.themoviedb.views.base

interface IBindableAdapter<T> {

    fun setData(items: List<T>)
    fun changedPositions(positions: Set<Int>)
}