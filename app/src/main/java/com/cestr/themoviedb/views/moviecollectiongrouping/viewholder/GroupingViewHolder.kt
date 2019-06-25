package com.cestr.themoviedb.views.moviecollectiongrouping.viewholder

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cestr.themoviedb.databinding.CellGroupingCollectionBinding
import com.cestr.themoviedb.interfaces.IOnMovieItemTouchListener
import com.cestr.themoviedb.model.CollectionGrouping
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.utils.Utils
import com.cestr.themoviedb.views.moviecollectiongrouping.adapter.MovieCollectionHorizontalGridAdapter
import kotlinx.android.synthetic.main.cell_grouping_collection.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class GroupingViewHolder(paramView: View) : RecyclerView.ViewHolder(paramView) {


    private lateinit var rvMoviesLayoutManager: LinearLayoutManager
    private lateinit var moviesGridAdapter: MovieCollectionHorizontalGridAdapter

    private var view: View? = null

    init {

        view = paramView

        initProperties()
    }

    fun initProperties() {

        initRecyclerView()

    }

    private fun initRecyclerView() {

        moviesGridAdapter = MovieCollectionHorizontalGridAdapter()
        rvMoviesLayoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

        itemView.rvMoviesGroupingCollection.layoutManager = rvMoviesLayoutManager

        itemView.rvMoviesGroupingCollection.adapter = moviesGridAdapter


        setRecyclerViewBouncingEffect()

    }


    private fun addEventHandlers() {

    }

    private fun removeEventHandlers() {

    }




    fun bind(movieCollection: CollectionGrouping<Int, MovieModel>, index: Int) {

        val cellBindableObject: CellGroupingCollectionBinding = DataBindingUtil.bind(itemView)!!

        cellBindableObject.movieCollection = movieCollection


        val a= Utils.genresCollection.getValue(movieCollection.key)

        itemView.txtCollectionTitle.text = a ?: "Other"



//        movieModel.imgUrl?.let {
//            itemView.movie_photo_imageView.loadUrl(movieModel.imgUrl)
//        }

    }


    private fun setRecyclerViewBouncingEffect() {

        OverScrollDecoratorHelper.setUpStaticOverScroll(itemView.rvMoviesGroupingCollection, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        val decor= OverScrollDecoratorHelper.setUpOverScroll(itemView.rvMoviesGroupingCollection, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL,false);
    }
}