package com.cestr.themoviedb.views.moviecollectiongrouping

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.cestr.themoviedb.R
import com.cestr.themoviedb.databinding.MoviecollectionGroupingactivityLayoutBinding
import com.cestr.themoviedb.interfaces.IOnMovieItemTouchListener
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.utils.Utils
import com.cestr.themoviedb.viewmodels.GroupingMovieListViewModel
import com.cestr.themoviedb.views.base.BaseActivity
import com.cestr.themoviedb.views.moviecollectiongrouping.adapter.MoviesGroupingCollectionGridAdapter
import com.cestr.themoviedb.views.moviedetail.MovieDetailActivity
import com.cestr.themoviedb.views.moviedetail.MovieDetailCollapsingToolbarActivity
import kotlinx.android.synthetic.main.moviecollection_groupingactivity_layout.*

class MovieCollectionGroupingActivity : BaseActivity() , IOnMovieItemTouchListener {

    private lateinit var moviesGroupingAdapter: MoviesGroupingCollectionGridAdapter
    private lateinit var groupingActivityViewModel: GroupingMovieListViewModel

    private lateinit var rvMoviesGridLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initProperties()
    }

    override fun onStart() {

        super.onStart()
        addEventHandlers()

    }

    override fun onStop() {

        removeEventHandlers()
        super.onStop()
    }

    override fun initProperties() {

        val binding: MoviecollectionGroupingactivityLayoutBinding = DataBindingUtil.setContentView(this, R.layout.moviecollection_groupingactivity_layout)
        groupingActivityViewModel= getViewModel( GroupingMovieListViewModel::class.java)

        binding.viewModel = groupingActivityViewModel

        initRecyclerView()
        initViewModel()
    }

    fun initViewModel() {
        groupingActivityViewModel.initVM()
    }

    private fun initRecyclerView() {

        moviesGroupingAdapter = MoviesGroupingCollectionGridAdapter()

        rvMoviesGridLayoutManager = LinearLayoutManager(this)


        rvMainMoviesCollection.layoutManager = rvMoviesGridLayoutManager

        rvMainMoviesCollection.adapter = moviesGroupingAdapter



    }

    override fun onMovieItemTouchedEvent(moviemodel: MovieModel) {

        val paramContext = Intent(this, MovieDetailCollapsingToolbarActivity::class.java)
        paramContext.putExtra(Utils.movieIdParam, moviemodel.id)
        paramContext.putExtra(Utils.defaultLanguage, true)
        this.startActivity(paramContext)
    }

    private fun addEventHandlers() {


    }

    private fun removeEventHandlers() {


    }
}