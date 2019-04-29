package com.cestr.themoviedb.views.moviedetail.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cestr.themoviedb.R
import com.cestr.themoviedb.model.MovieVideoModel
import com.cestr.themoviedb.views.base.IBindableAdapter
import com.cestr.themoviedb.views.main.viewholder.ItemViewHolder
import com.cestr.themoviedb.views.moviedetail.viewholder.VideoItemViewHolder


class TrailerItemsRecyclerViewAdapter : RecyclerView.Adapter<VideoItemViewHolder>(), IBindableAdapter<MovieVideoModel> {


        var videos = mutableListOf<MovieVideoModel>()

        override fun getItemCount() = videos.size


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            return VideoItemViewHolder(inflater.inflate(R.layout.cell_trailers_list, parent, false))
        }

        override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {

            holder.bind(videos[position]);
        }

        override fun changedPositions(positions: Set<Int>) {

//            positions.forEach(this::notifyItemChanged)
        }

        override fun setData(items: List<MovieVideoModel>) {
            videos.addAll(items)

            notifyDataSetChanged()
        }

    }
