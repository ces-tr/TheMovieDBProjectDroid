package com.cestr.themoviedb.views.moviecollectiongrouping.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cestr.themoviedb.R
import com.cestr.themoviedb.model.CollectionGrouping
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.views.base.IBindableAdapter
import com.cestr.themoviedb.views.moviecollectiongrouping.viewholder.GroupingViewHolder


class MoviesGroupingCollectionGridAdapter: ListAdapter<CollectionGrouping<Int, MovieModel>, RecyclerView.ViewHolder>(ITEM_COMPARATOR),
    IBindableAdapter<CollectionGrouping<Int, MovieModel>>
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        var holder: RecyclerView.ViewHolder? = null


        holder = GroupingViewHolder(inflater.inflate(R.layout.cell_grouping_collection, parent, false))


        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {

        val movieCollection = getItem(p1)

        if (holder is GroupingViewHolder) {

            holder.bind(movieCollection, p1)
        }
    }




    override fun setData(items: List<CollectionGrouping<Int,MovieModel>>) {

        submitList(items);
        notifyDataSetChanged()
    }

    companion object {
        // The position of the header in the zero-based list
        const val HEADER_POSITION = 0
        const val ITEM_PROGRESS = -1


        // A DiffUtil.ItemCallback for calculating the diff between two non-null items in a list.

        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<CollectionGrouping<Int, MovieModel>>() {
            override fun areContentsTheSame(
                oldItem: CollectionGrouping<Int, MovieModel>,
                newItem: CollectionGrouping<Int, MovieModel>
            ): Boolean {

//                return oldItem.key == newItem.key && oldItem.size == newItem.size

                return oldItem.equals(newItem);
            }

            override fun areItemsTheSame(
                oldItem: CollectionGrouping<Int, MovieModel>,
                newItem: CollectionGrouping<Int, MovieModel>
            ): Boolean {

//                return oldItem.equals(newItem);
                return oldItem.key == newItem.key
            }
        }
    }

}