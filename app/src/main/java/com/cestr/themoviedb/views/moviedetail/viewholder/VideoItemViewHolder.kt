package com.cestr.themoviedb.views.moviedetail.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.cestr.themoviedb.model.MovieVideoModel
import kotlinx.android.synthetic.main.cell_trailers_list.view.*

class VideoItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    fun bind(movievideoModel: MovieVideoModel){

        itemView.txtvideoName.text = movievideoModel.name
    }

}