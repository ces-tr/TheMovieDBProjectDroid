package com.cestr.themoviedb.views.moviedetail

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.cestr.themoviedb.R
import com.cestr.themoviedb.databinding.MoviedetailCollapsingtoolbaractivityLayoutBinding
import com.cestr.themoviedb.views.base.BaseActivity
import android.support.design.widget.AppBarLayout
import com.cestr.themoviedb.utils.Utils
import com.cestr.themoviedb.viewmodels.MovieDetailViewModel


class MovieDetailCollapsingToolbarActivity : BaseActivity() {


    private lateinit var movieDetailViewModel: MovieDetailViewModel
    var movieId:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initProperties()
    }

    override fun initProperties() {

        val binding: MoviedetailCollapsingtoolbaractivityLayoutBinding = DataBindingUtil.setContentView(this, R.layout.moviedetail_collapsingtoolbaractivity_layout)

        initToolbar()

        if (intent.extras!!.containsKey(Utils.movieIdParam)) {
            movieId=   intent.extras.getInt(Utils.movieIdParam)
        }

        movieDetailViewModel= getViewModel( MovieDetailViewModel::class.java)
        binding.viewModel = movieDetailViewModel

        initViewModel()
    }


    private fun initToolbar() {

        val toolbar = findViewById<Toolbar>(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        val appBarLayout = findViewById<AppBarLayout>(R.id.appBar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, verticalOffset ->
            val range = (-appBar.totalScrollRange).toFloat()

            val alpha= 255- (255 * (1.0f - verticalOffset.toFloat() / range)).toInt()

            toolbar.setBackgroundColor(Color.argb(alpha, 0,0,0));
//          profileImage.setImageAlpha()
        })
    }

    fun initViewModel() {
        movieId?.let {

            movieDetailViewModel.initVM(it,true)
        }

    }


}