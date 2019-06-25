package com.cestr.themoviedb.viewmodels

import android.annotation.SuppressLint
import android.databinding.Bindable
import android.util.Log
import com.cestr.themoviedb.BR
import com.cestr.themoviedb.dto.MovieResponseWrapper
import com.cestr.themoviedb.dto.toMovieModel
import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.utils.ActionLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(moviesManager: IMovieManager): MovieDetailViewModel(moviesManager)
{

    var isMovieDetailsEnabled: Boolean =false
    val onfinishedfetchingMovieData = ActionLiveData<Any?>()

    @Bindable
    var moviesCollection: MutableList<MovieModel>? =null
        private set(value) {
            field = value
            notifyPropertyChanged(BR.moviesCollection)
        }

    var movieResponseWrapper : MovieResponseWrapper? = null;

    init{
        moviesCollection = mutableListOf<MovieModel>()
    }


    //PAGINATION
    var currentPage: Int? = null
    var totalPages: Int? = null

    var orderByMostPopular: Boolean=false
    var orderByHigherRating: Boolean=false

    var defaultLanguage =true


    @SuppressLint("CheckResult")
    fun initVM() {

        loadData()
    }

    @SuppressLint("CheckResult")
    private fun fetchMoviesData() :Observable<MovieResponseWrapper> {

        var pageToFetch:Int?= null
        currentPage.let{
            pageToFetch = currentPage?.plus(1)
        }

        var languageParameter:String? = null
        if(!defaultLanguage) {
            languageParameter = "es"
        }

        var orderbyMostPopular:String?=null
        if(orderByMostPopular){
            orderbyMostPopular="popularity.desc"
        }

        val fetchMoviesObserver: Observable<MovieResponseWrapper>? = moviesManager.getPopularMovies(orderBy= orderbyMostPopular,page =  pageToFetch,language =  languageParameter)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())


        return fetchMoviesObserver!!;
    }

    private fun movieResponse (responseWrapper : MovieResponseWrapper): Observable<MutableList<MovieModel>>? {

        currentPage = responseWrapper.page

        return Observable.fromIterable(responseWrapper.results)
                            .map { it -> it.toMovieModel() }
                            .toList()
                            .toObservable()
                            .doOnError {
                                Log.d("movieResponse","error getting movie model from movie Response Wrapper"+it.message)

                            }
    }

    @SuppressLint("CheckResult")
    fun loadData() {
        try {
            synchronized(this) {
                if (isBusy) {
                    return
                }

                isBusy = true;
            }

            fetchMoviesData().subscribe() {
                it?.let {

                    movieResponse(it)?.let { movielistObservable ->

                        movielistObservable
                            .doAfterTerminate {

                                isBusy = false
                                onfinishedfetchingMovieData?.sendAction(true)
                            }
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe(

                                { movieList ->
                                    movieList?.let {

                                        moviesCollection?.addAll(it)
                                        notifyPropertyChanged(BR.moviesCollection)

                                        if (isMovieDetailsEnabled) {
                                            initVMWithMovieData()
                                            isMovieDetailsEnabled = false
                                        }

                                        testGrouping()

                                    }
                                },
                                {

                                },
                                {

                                }
                            )
                    }
                }

            }
        }
        catch (e:Exception)  {


        }
    }

    private fun testGrouping() {

        val hashMap = HashMap<Int, MutableList<MovieModel>>()

        moviesCollection?.let {

            for ( item in it ) {

                for (key:Int in item.genre_ids!! ) {

//                    val keyValue= hashMap.getOrPut(key, {
//                        mutableListOf<MovieModel>(item)
//                    })

                    if(!hashMap.containsKey(key)) {
                        val list =mutableListOf<MovieModel>(item)
                        hashMap[key] = list

                    }
                    else {
                        val keyValue= hashMap[key]
                        keyValue?.add(item)

                    }

                }
            }

        }

    }

    fun orderListByMostPopular() {

    }

    fun initMovieDetails(id: Int) {
        initVM(id, defaultLanguage)
    }

    @SuppressLint("CheckResult")
    fun refreshMoviesData()
    {
        currentPage =null
        fetchMoviesData().subscribe() {
            movieResponse(it!!)?.subscribe(){
                it?.let {

                    moviesCollection?.clear()
                    moviesCollection?.addAll(it)
                    notifyPropertyChanged(BR.moviesCollection)
                }
            }
        }
    }

    fun initVMWithMovieData() {

        try {
            val index = moviesCollection?.get(0)?.id

            if (index != null) {
                initMovieDetails(index)
            }

        } catch (e: Exception) {
        }
    }
}