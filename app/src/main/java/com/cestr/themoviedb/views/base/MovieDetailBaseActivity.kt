package com.cestr.themoviedb.views.base


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.cestr.themoviedb.R
import com.cestr.themoviedb.views.moviedetail.adapter.TrailerItemsRecyclerViewAdapter

open class MovieDetailBaseActivity : BaseActivity() {

    var movieId:Int?=null   ;
    var defaultLanguageSelected:Boolean=true

    lateinit  var trailerListAdapter : TrailerItemsRecyclerViewAdapter

    override fun initProperties() {


    }

    protected fun initTrailersRecyclerView() {


        trailerListAdapter = TrailerItemsRecyclerViewAdapter()

        val recyclerView:RecyclerView =findViewById(R.id.rvTrailersList)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter= trailerListAdapter
        recyclerView.isNestedScrollingEnabled = false;
    }


}