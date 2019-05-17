package com.cestr.themoviedb.views.main.adapter

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cestr.themoviedb.views.main.viewholder.ImageViewHolder
import com.cestr.themoviedb.utils.ActionLiveData

import com.cestr.themoviedb.R

import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.views.base.IBindableAdapter
import com.cestr.themoviedb.views.main.viewholder.BottomLoadingViewHolder
import org.jetbrains.annotations.NotNull




class MoviesGridAdapter : ListAdapter<MovieModel, RecyclerView.ViewHolder>(ITEM_COMPARATOR), IBindableAdapter<MovieModel> {

    val LAST_ITEM_REACHED_THRESHOLD = 3

//    var productList = mutableListOf<MovieModel>()

    var context:Context? = null ;

    val onItemTapped = ActionLiveData<MovieModel>()

    val onLastItemReached = ActionLiveData<Any>()

//    override fun getItemCount(): Int {
//        return productList.size
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        context =parent.context;

        var holder: RecyclerView.ViewHolder? = null

//         if (viewType == ITEM_PROGRESS)
//             holder = BottomLoadingViewHolder(inflater.inflate(R.layout.cell_bottom_loading, parent,false ))
//        else
             holder = ImageViewHolder(inflater.inflate(R.layout.cell_movies_grid, parent, false))


        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {

        val movie = getItem(p1)

//        holder.bind(movie,p1)

        if (holder is ImageViewHolder) {
            holder.bind(movie, p1)


            removeEventHandlers(holder)
            addEventHandlers(holder,p1)
        }
//        else {
//            (holder as BottomLoadingViewHolder).bind(true)
//        }


//        initViewHolder(holder, movie, p1)
//


//        checkBoundItemPosition(p1) //Uncomment to enable fetching data onscrollover

    }

    fun checkBoundItemPosition(p1: Int) {

        if (p1 >= itemCount - 1){

            fetchMoreData();

        }
    }

    private fun fetchMoreData(){

        onLastItemReached.sendAction(Any())
    }

    private fun addEventHandlers(holder: ImageViewHolder, p1: Int)
    {
        holder.itemView.setOnClickListener {
            onItemTapped.sendAction(getItem(p1));
        };
    }

    private fun removeEventHandlers(holder: ImageViewHolder)
    {
        holder.itemView.setOnClickListener(null)
    }

    override fun setData(items: List<MovieModel>) {

//        productList.addAll(items)   ;
        this.submitList(items);
        notifyDataSetChanged()
   //     notifyItemRangeChanged(0, items.size -1);

    }

    override fun changedPositions(positions: Set<Int>) {

    }


    override fun getItemViewType(position: Int): Int {

//        if (getItem(position).id == ITEM_PROGRESS)
//            return ITEM_PROGRESS

        return super.getItemViewType(position)
    }


    companion object {
        // The position of the header in the zero-based list
        const val HEADER_POSITION = 0
        const val ITEM_PROGRESS = -1


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

