package com.cestr.themoviedb.views.moviedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.cestr.themoviedb.R
import com.cestr.themoviedb.databinding.DetailactivityLayoutBinding
import com.cestr.themoviedb.utils.Utils
import com.cestr.themoviedb.viewmodels.MovieDetailViewModel
import com.cestr.themoviedb.views.base.BaseActivity
import com.cestr.themoviedb.views.base.MovieDetailBaseActivity
import com.cestr.themoviedb.views.moviedetail.adapter.TrailerItemsRecyclerViewAdapter
import kotlinx.android.synthetic.main.detailactivity_layout.*

private lateinit var movieDetailViewModel: MovieDetailViewModel

class MovieDetailActivity : MovieDetailBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initProperties()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
    }


    override fun initProperties()
    {
        if (intent.extras!!.containsKey(Utils.movieIdParam)){
            movieId=   intent.extras.getInt(Utils.movieIdParam)
        }
        if (intent.extras!!.containsKey(Utils.defaultLanguage)){
            defaultLanguageSelected=   intent.extras.getBoolean(Utils.defaultLanguage)
        }

        val binding: DetailactivityLayoutBinding = DataBindingUtil.setContentView(this, R.layout.detailactivity_layout)

        movieDetailViewModel= getViewModel( MovieDetailViewModel::class.java)
        binding.viewModel = movieDetailViewModel


        initTrailersRecyclerView()

    }

    override fun onStart() {

        super.onStart()
        initViewModel()


    }

    fun initViewModel( ) {
        movieId?.let {

            movieDetailViewModel.initVM(it, defaultLanguageSelected!!)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}