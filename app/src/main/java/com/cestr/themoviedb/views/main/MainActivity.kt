package com.cestr.themoviedb.views.main

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.cestr.themoviedb.R
import com.cestr.themoviedb.databinding.MainactivityLayoutBinding
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.utils.Utils
import com.cestr.themoviedb.viewmodels.MainActivityViewModel
import com.cestr.themoviedb.views.main.adapter.MoviesGridAdapter
import com.cestr.themoviedb.views.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.mainactivity_layout.*
import android.view.MenuItem

import android.widget.Toast
import android.content.res.Configuration
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.view.View
import com.cestr.themoviedb.utils.getMultiChoiceAlertDialog
import com.cestr.themoviedb.utils.getSingleChoiceAlertDialog
import com.cestr.themoviedb.views.base.MovieDetailBaseActivity


class MainActivity : MovieDetailBaseActivity() {


    private var shouldSupportEmbeddedMovieDetail: Boolean=false

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var rvMoviesGridLayoutManager: GridLayoutManager
    private lateinit var moviesGridAdapter : MoviesGridAdapter

    private var settingsDialogBuilder :AlertDialog.Builder? =null
    private var languageDialogBuilder :AlertDialog.Builder? =null

    private var languageDialog: AlertDialog? =null
    private var settingsDialog: AlertDialog? =null

    val settingsItems = arrayOf("Order by Popularity", "Order by Higher Rating")
    val languageItems = arrayOf("English","Spanish" )

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

    override fun initProperties () {

        //type derived from mainactivity_layout
        val binding: MainactivityLayoutBinding = DataBindingUtil.setContentView(this, R.layout.mainactivity_layout)

        mainActivityViewModel= getViewModel( MainActivityViewModel::class.java)

        binding.viewModel = mainActivityViewModel

        initDialogs()
        initRecyclerView()
        initViewModel()

        supportEmbeddedDetail()

        if(shouldSupportEmbeddedMovieDetail){
            initTrailersRecyclerView()
            mainActivityViewModel.initVMWithMovieData()
        }
    }

    fun initDialogs() {

        settingsDialogBuilder= getMultiChoiceAlertDialog(this, settingsItems,"Settings", { index: Int, isSelected: Boolean ->

            Toast.makeText(this, settingsItems[index], Toast.LENGTH_SHORT).show()

            when(settingsItems[index]) {
                "Order by Popularity" -> {
                    mainActivityViewModel.orderByMostPopular = isSelected
                }

                "Order by Higher Rating" -> {
                    mainActivityViewModel.orderByHigherRating = isSelected
                }
            }

        }, cancelCallback = {
            mainActivityViewModel.orderByHigherRating = !mainActivityViewModel.orderByMostPopular
            mainActivityViewModel.orderByMostPopular= !mainActivityViewModel.orderByMostPopular

        },acceptCallback={

            mainActivityViewModel.refreshMoviesData()
        })

        languageDialogBuilder= getSingleChoiceAlertDialog(this, languageItems,"Language") { index: Int, isSelected: Boolean ->

            Toast.makeText(this, languageItems[index], Toast.LENGTH_SHORT).show()

            when(languageItems[index]) {

                "English" -> {
                    mainActivityViewModel.defaultLanguage = true

                }

                "Spanish" -> {
                    mainActivityViewModel.defaultLanguage = false
                }
            }

            languageDialog?.dismiss()
            mainActivityViewModel.refreshMoviesData()
        }
    }

    private fun initRecyclerView() {

        rvMoviesGridLayoutManager = GridLayoutManager(this, 3)
        rvMoviesGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(paramAnonymousInt: Int): Int {
                return 1
            }
        }

        moviesGridAdapter = MoviesGridAdapter()


        rvMoviesCollection.layoutManager= rvMoviesGridLayoutManager

        rvMoviesCollection.adapter = moviesGridAdapter
    }

    private fun initViewModel() {

        mainActivityViewModel.initVM()

    }


    private fun addEventHandlers(){

        moviesGridAdapter.onItemTapped.observe(this, onMoviesGridItemTapped )
        moviesGridAdapter.onLastItemReached.observe(this, onMoviesGridLastItemReached)
    }

    private fun removeEventHandlers(){

        moviesGridAdapter.onItemTapped.removeObserver( onMoviesGridItemTapped )
        moviesGridAdapter.onLastItemReached.removeObserver( onMoviesGridLastItemReached)
    }


    val onMoviesGridItemTapped= Observer<MovieModel?>  {

        if(it != null) {
            if(shouldSupportEmbeddedMovieDetail){
                mainActivityViewModel.initMovieDetails(it.id)

            }else {
                val paramContext = Intent(this, MovieDetailActivity::class.java)
                paramContext.putExtra(Utils.movieIdParam, it.id)
                paramContext.putExtra(Utils.defaultLanguage, mainActivityViewModel.defaultLanguage)
                this.startActivity(paramContext)
            }
        }

    }

    val onMoviesGridLastItemReached= Observer<Any?>  {

        mainActivityViewModel.fetchMoreData()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val settingsMenu: MenuItem =menu.findItem(R.id.action_settings)



        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.getItemId()

        when(id) {
            R.id.action_settings -> {
                onSettingsMenuItemTapped()
            }
            R.id.action_language -> {
                onLanguageMenuItemTapped()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun onSettingsMenuItemTapped() {
        if(settingsDialog!= null) {

            settingsDialog?.show()

        }else {
            settingsDialog = settingsDialogBuilder?.show()!!
        }
    }

    fun onLanguageMenuItemTapped() {

        if(languageDialog != null) {

            languageDialog?.show()
        }else {
            languageDialog = languageDialogBuilder?.show()!!
        }
    }


    private fun supportEmbeddedDetail() {

        val orientation = this.getResources().getConfiguration().orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val embeddedDetail: View?= findViewById<NestedScrollView>(R.id.embeddedDetailView)
            if(embeddedDetail!= null){

                shouldSupportEmbeddedMovieDetail =true
            }
        }else{

            shouldSupportEmbeddedMovieDetail =false
        }

    }


}
