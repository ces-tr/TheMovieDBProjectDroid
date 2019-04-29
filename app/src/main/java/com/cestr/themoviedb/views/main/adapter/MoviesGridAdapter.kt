package com.cestr.themoviedb.views.main.adapter

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cestr.testkotlin.views.main.ImageViewHolder
import com.cestr.themoviedb.utils.ActionLiveData

import com.cestr.themoviedb.R

import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.views.base.IBindableAdapter
import org.jetbrains.annotations.NotNull




class MoviesGridAdapter : ListAdapter<MovieModel, ImageViewHolder>(ITEM_COMPARATOR), IBindableAdapter<MovieModel> {

    val LAST_ITEM_REACHED_THRESHOLD = 3

//    var productList = mutableListOf<MovieModel>()

    var context:Context? = null ;

    val onItemTapped = ActionLiveData<MovieModel>()

    val onLastItemReached = ActionLiveData<Any>()

//    override fun getItemCount(): Int {
//        return productList.size
//    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ImageViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        context =parent.context;

        val holder: ImageViewHolder = ImageViewHolder(inflater.inflate(R.layout.cell_movies_grid, parent, false))

        return holder
    }

    override fun onBindViewHolder(holder: ImageViewHolder, p1: Int) {

        val movie = getItem(p1)

        holder.bind(movie,p1)


        initViewHolder(holder, movie, p1)

        RemoveEventHandlers(holder)
        AddEventHandlers(holder,p1)

        checkBoundItemPosition(p1)

    }

    fun checkBoundItemPosition(p1: Int) {

        if (p1 >= getItemCount() - 1){
            fetchMoreData();

        }
    }

    fun fetchMoreData(){
        onLastItemReached.sendAction(Any())
    }

    private fun AddEventHandlers(holder: ImageViewHolder, p1: Int)
    {
        holder.itemView.setOnClickListener({
            onItemTapped.sendAction(getItem(p1));
        });
    }

    private fun RemoveEventHandlers(holder: ImageViewHolder)
    {
        holder.itemView.setOnClickListener(null)
    }

    override fun setData(items: List<MovieModel>) {

//        productList.addAll(items)   ;
        this.submitList(items);
        notifyDataSetChanged()

    }

    override fun changedPositions(positions: Set<Int>) {

    }


    private fun initViewHolder(
        @NotNull productViewHolder: ImageViewHolder,
        @NotNull movie: MovieModel,
        index: Int
    ) {

    }


    companion object {
        // The position of the header in the zero-based list
        const val HEADER_POSITION = 0

        // A DiffUtil.ItemCallback for calculating the diff between two non-null items in a list.

        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areContentsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ): Boolean {

                //return oldItem.id == newItem.id
                return oldItem.equals(newItem);
            }

            override fun areItemsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ): Boolean {

                return oldItem.id == newItem.id
            }
        }
    }


}

