package com.cestr.themoviedb.views.base

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.*
import com.cestr.themoviedb.views.MovieImageView

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: MovieImageView, url: String?) {

    imageView.loadUrl(url!!)
//    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>) {
    if (recyclerView.adapter is IBindableAdapter<*>) {
        (recyclerView.adapter as IBindableAdapter<T>).setData(items)
    }
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

@BindingAdapter("visible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}