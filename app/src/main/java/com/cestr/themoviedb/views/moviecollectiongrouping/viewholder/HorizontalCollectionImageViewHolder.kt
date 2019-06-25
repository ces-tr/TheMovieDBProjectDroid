package com.cestr.themoviedb.views.moviecollectiongrouping.viewholder

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cestr.themoviedb.databinding.CellMoviesHorizontalGridBinding
import com.cestr.themoviedb.interfaces.IOnMovieItemTouchListener
import com.cestr.themoviedb.model.MovieModel
import kotlinx.android.synthetic.main.cell_movies_grid.view.*

class HorizontalCollectionImageViewHolder(paramView: View) : RecyclerView.ViewHolder(paramView) {



    private val cellBindableObject: CellMoviesHorizontalGridBinding = DataBindingUtil.bind(itemView)!!

    private var view: View? = null

    init {
        view= paramView
    }

    fun bind(movieModel: MovieModel, paramInt: Int) {

        cellBindableObject.movie =movieModel

        movieModel.imgUrl?.let {
            itemView.movie_photo_imageView.loadUrl(movieModel.imgUrl)
        }

    }



    private fun onItemTouched(it: View): View.OnClickListener? {

            if(it.context is IOnMovieItemTouchListener){

            }

        return null
    }

    fun getView(): View {

        return this.itemView!!
    }


}