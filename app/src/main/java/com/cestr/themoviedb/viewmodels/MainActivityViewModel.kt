package com.cestr.themoviedb.viewmodels

import android.annotation.SuppressLint
import android.databinding.Bindable
import com.cestr.themoviedb.BR
import com.cestr.themoviedb.dto.MovieResponseWrapper
import com.cestr.themoviedb.dto.toMovieModel
import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.model.MovieModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(moviesManager: IMovieManager): MovieDetailViewModel(moviesManager)
{
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
        try {

            fetchMoviesData().subscribe(){
                finishedfetchingMovieData(it)
            }
        }
        catch (e:Exception ){


        }
//        fetchMoviesData()
    }

    private fun finishedfetchingMovieData(it: MovieResponseWrapper?) {

        it.let {

            movieResponse(it!!)?.subscribe(){
                it.let {

                    moviesCollection?.addAll(it)
                    notifyPropertyChanged(BR.moviesCollection)
                }
            }
        }

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

    fun movieResponse (responseWrapper : MovieResponseWrapper): Observable<MutableList<MovieModel>>? {

        currentPage = responseWrapper.page

        val response= Observable.fromIterable(responseWrapper.results)
                            .map({
                                it -> it.toMovieModel()
                            }).toList()
                        .toObservable()

        return  response
    }

    @SuppressLint("CheckResult")
    fun fetchMoreData() {
        try{

            fetchMoviesData().subscribe(){
                finishedfetchingMovieData(it)

            }
        }
        catch (e:Exception)  {


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
                it.let {

                    moviesCollection?.clear()
                    moviesCollection?.addAll(it)
                    notifyPropertyChanged(BR.moviesCollection)
                }
            }
        }
    }

    fun initVMWithMovieData() {

        val index =moviesCollection?.get(0)?.id

        if(index != null) {
            initMovieDetails(index)
        }


    }

}










//        fetchMoviesObserver?.flatMap({
//                                movieResponse(it)
//                            })
//                            ?.subscribe()
//                            {
//                                it.let {
//
//                                    moviesCollection?.addAll(it)
//                                    notifyPropertyChanged(BR.moviesCollection)
//                                }
//                            }


//            moviesManager?.getTopRatedMovies()
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .flatMap({
//                    movieResponse(it)
//                })
//                .subscribe(
//                {
//                    it.let {
//                        moviesCollection?.addAll(it)
//                        notifyPropertyChanged(BR.moviesCollection)
//
//                    }
//                })