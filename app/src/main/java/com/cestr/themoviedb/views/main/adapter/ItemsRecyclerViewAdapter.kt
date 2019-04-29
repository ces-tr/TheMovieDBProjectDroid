package com.cestr.themoviedb.views.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cestr.themoviedb.R
import com.cestr.themoviedb.views.base.IBindableAdapter
import com.cestr.themoviedb.views.main.viewholder.ItemViewHolder


class ItemsRecyclerViewAdapter : RecyclerView.Adapter<ItemViewHolder>(), IBindableAdapter<Long> {


    var userIds = emptyList<Long>()

    override fun getItemCount() = userIds.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.cell_movies_grid, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(userIds[position]);
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }

    override fun setData(items: List<Long>) {
        userIds = items
        notifyDataSetChanged()
    }

}