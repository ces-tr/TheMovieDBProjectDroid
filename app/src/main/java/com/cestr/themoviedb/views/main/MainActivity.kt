package com.cestr.themoviedb.views.main

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
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
import android.os.Handler
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.view.View
import com.cestr.themoviedb.utils.getMultiChoiceAlertDialog
import com.cestr.themoviedb.utils.getSingleChoiceAlertDialog
import com.cestr.themoviedb.views.base.MovieDetailBaseActivity
import android.support.v4.widget.SwipeRefreshLayout

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import me.everything.android.ui.overscroll.IOverScrollUpdateListener




class MainActivity : MovieDetailBaseActivity() , SwipeRefreshLayout.OnRefreshListener{

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

    private lateinit var swipeContainer:SwipeRefreshLayout;

    private var topOverScrolling:Boolean = false
    private var bottomOverScrolling:Boolean = false


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


        initSwipeToRefresh()
        initDialogs()
        initRecyclerView()


        supportEmbeddedDetail()

        if(shouldSupportEmbeddedMovieDetail) {

            initTrailersRecyclerView()
            mainActivityViewModel.isMovieDetailsEnabled=true
//            mainActivityViewModel.initVMWithMovieData()
        }
        initViewModel()
    }

    private fun initSwipeToRefresh() {

        swipeContainer=  findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        swipeContainer?.setOnRefreshListener(this)
    }

    private fun initDialogs() {

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

        moviesGridAdapter = MoviesGridAdapter()

        rvMoviesGridLayoutManager = GridLayoutManager(this, 3)
        rvMoviesGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(paramInt: Int): Int {

               var span= when(moviesGridAdapter.getItemViewType(paramInt)){

                    -1 -> 3

                    else -> 1
                }

                return span
            }
        }

        rvMoviesCollection.layoutManager = rvMoviesGridLayoutManager

        rvMoviesCollection.adapter = moviesGridAdapter

        OverScrollDecoratorHelper.setUpStaticOverScroll(rvMoviesCollection, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        val decor= OverScrollDecoratorHelper.setUpOverScroll(rvMoviesCollection, OverScrollDecoratorHelper.ORIENTATION_VERTICAL,true);

        decor.setOverScrollStateListener {
                _, oldState, newState ->

                    run {

//                        when (newState) {
//
//                            IOverScrollState.STATE_BOUNCE_BACK -> {
//                                mainActivityViewModel.refreshMoviesData()
//
//                            }
//                            else -> {}
//
//                        }

                    }
        }
        decor.setOverScrollUpdateListener(

            IOverScrollUpdateListener { _, state, offset ->
                run {

                    if (offset > 0) {
                        topOverScrolling =true // 'view' is currently being over-scrolled from the top.
                    } else if (offset < 0) {

                        bottomOverScrolling=true; // 'view' is currently being over-scrolled from the bottom.

//                        val translationY = rvMoviesCollection.translationY //- pbBottom.height
//                        pbBottom.translationY = translationY + offset*0.35f;

//                        ViewCompat.animate(pbBottom).translationY(translationY)

                    } else {

                        if(bottomOverScrolling)
                            mainActivityViewModel.loadData()

                        topOverScrolling =false
                        bottomOverScrolling=false
//                        ViewCompat.animate(pbBottom).translationY(0f).start()

                        // No over-scroll is in-effect.
                        // This is synonymous with having (state == STATE_IDLE).
                    }

                }

        })




    }

    private fun initViewModel() {

        mainActivityViewModel.initVM()

    }

    private fun addEventHandlers() {

        moviesGridAdapter.onItemTapped.observe(this, onMoviesGridItemTapped )
        moviesGridAdapter.onLastItemReached.observe(this, onMoviesGridLastItemReached)
        mainActivityViewModel.onfinishedfetchingMovieData.observe(this, onfinishedfetchingMovieData )
    }

    private fun removeEventHandlers(){

        moviesGridAdapter.onItemTapped.removeObserver( onMoviesGridItemTapped )
        moviesGridAdapter.onLastItemReached.removeObserver( onMoviesGridLastItemReached)
        mainActivityViewModel.onfinishedfetchingMovieData.removeObserver(onfinishedfetchingMovieData)
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

        mainActivityViewModel.loadData()

    }


    val onfinishedfetchingMovieData =  Observer<Any?>  {


        ViewCompat.animate(pbBottom).translationY(0f).start()

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

        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val embeddedDetail: View?= findViewById<NestedScrollView>(R.id.embeddedDetailView)
            if(embeddedDetail!= null){

                shouldSupportEmbeddedMovieDetail =true
            }
        }else{

            shouldSupportEmbeddedMovieDetail =false
        }

    }

    override fun onRefresh() {
        // Your code to make your refresh action
        // CallYourRefreshingMethod();
        mainActivityViewModel.refreshMoviesData()
        val handler = Handler()
        handler.postDelayed(Runnable {
            if (swipeContainer.isRefreshing()) {
                swipeContainer.setRefreshing(false)
            }
        }, 1000)

    }




}
