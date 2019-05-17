package com.cestr.themoviedb.views.main.viewholder

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cestr.themoviedb.databinding.CellMoviesGridBinding

import com.cestr.themoviedb.model.MovieModel
import kotlinx.android.synthetic.main.cell_movies_grid.view.*


class ImageViewHolder(paramView: View) : RecyclerView.ViewHolder(paramView) {

    private val cellBindableObject: CellMoviesGridBinding = DataBindingUtil.bind(itemView)!!

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



    fun getView(): View {

        return this.itemView!!
    }


}