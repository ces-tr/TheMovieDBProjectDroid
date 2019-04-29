package com.cestr.themoviedb.views.main.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

import com.cestr.themoviedb.model.MovieModel
import kotlinx.android.synthetic.main.cell_movies_grid.view.*


class ImageViewHolder(paramView: View) : RecyclerView.ViewHolder(paramView) {


    private var view: View? = null

        init {
            view= paramView
        }

        fun bind(productDataBean: MovieModel, paramInt: Int) {

                itemView.movie_photo_imageView.loadUrl(productDataBean.imgUrl)
        }



    fun getView(): View {
                return this.itemView!!
    }


}