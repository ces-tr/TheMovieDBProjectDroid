package com.cestr.themoviedb.viewmodels

import android.annotation.SuppressLint
import android.databinding.Bindable
import android.util.Log
import com.cestr.themoviedb.BR
import com.cestr.themoviedb.dto.MovieResponseWrapper
import com.cestr.themoviedb.dto.toMovieModel
import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.model.CollectionGrouping
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.utils.ActionLiveData
import com.cestr.themoviedb.utils.Utils
import com.cestr.themoviedb.viewmodels.base.MovieBaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GroupingMovieListViewModel @Inject constructor(moviesManager: IMovieManager) : MovieBaseViewModel(moviesManager) {


    var isMovieDetailsEnabled: Boolean =false
    val onfinishedfetchingMovieData = ActionLiveData<Any?>()

    @Bindable
    var moviesCollection: MutableList<MovieModel>? =null
        private set(value) {
            field = value
            notifyPropertyChanged(BR.moviesCollection)
        }

    var movieResponseWrapper : MovieResponseWrapper? = null;

    @Bindable
    var groupingMoviesCollection: MutableList<CollectionGrouping<Int,MovieModel>>

    init {
        moviesCollection = mutableListOf<MovieModel>()
        groupingMoviesCollection = mutableListOf<CollectionGrouping<Int, MovieModel>>()
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
    fun loadData() {
        try {
            synchronized(this) {
                if (isBusy) {
                    return
                }

                isBusy = true;
            }

            for( item in Utils.genresCollection) {

                fetchMoviesDataByGenre(item.key).subscribe(){

                    it?.let {

                        movieResponse(it)?.let { movielistObservable ->

                            movielistObservable
                                .doAfterTerminate {

                                    isBusy = false
                                    onfinishedfetchingMovieData.sendAction(true)
                                }
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(

                                    { movieListResponse ->
                                        movieListResponse?.let { movieList ->

                                            //                                            moviesCollection?.addAll(it)
//                                            notifyPropertyChanged(BR.moviesCollection)

                                            createGroupingCollectionForGenre(item.key, movieList)

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


//            fetchMoviesData().subscribe() {
//                it?.let {
//
//                    movieResponse(it)?.let { movielistObservable ->
//
//                        movielistObservable
//                            .doAfterTerminate {
//
//                                isBusy = false
//                                onfinishedfetchingMovieData.sendAction(true)
//                            }
//                            .subscribeOn(AndroidSchedulers.mainThread())
//                            .subscribe(
//
//                                { movieList ->
//                                    movieList?.let {
//
//                                        moviesCollection?.addAll(it)
//                                        notifyPropertyChanged(BR.moviesCollection)
//
//                                        createGrouping()
//
//                                    }
//                                },
//                                {
//
//                                },
//                                {
//
//                                }
//                            )
//                    }
//                }
//
//            }


        }
        catch (e:Exception)  {

            Log.d("error",e.message)
        }

    }

    @SuppressLint("CheckResult")
    private fun fetchMoviesData() : Observable<MovieResponseWrapper> {

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


    @SuppressLint("CheckResult")
    private fun fetchMoviesDataByGenre(genreId:Int) : Observable<MovieResponseWrapper> {

//        var pageToFetch:Int?= null
//        currentPage.let{
//            pageToFetch = currentPage?.plus(1)
//        }

        var languageParameter:String? = null
        if(!defaultLanguage) {
            languageParameter = "es"
        }

        var orderbyMostPopular:String?=null
        if(orderByMostPopular){
            orderbyMostPopular="popularity.desc"
        }

//        val fetchMoviesObserver: Observable<MovieResponseWrapper> = moviesManager.getPopularMovies(orderBy= orderbyMostPopular,page =  pageToFetch,language =  languageParameter)
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.io())


            return moviesManager.getMoviesByGenre(genreId.toString())
                    .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

//        return fetchMoviesObserver;
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

    @SuppressLint("UseSparseArrays")
    private fun createGroupingCollectionForGenre(
        genreId: Int,
        movieList: MutableList<MovieModel>
    ) {

        movieList.let {


            val hashMapCollection: HashMap<Int, MutableList<MovieModel>> = HashMap()

            for (item in it) {

//                for (key:Int in item.genre_ids!! ) {

//                    val keyValue= hashMap.getOrPut(key, {
//                        mutableListOf<MovieModel>(item)
//                    })

//                    hashMap?.let { hashMapCollection->

                if ((hashMapCollection.containsKey(genreId))) {

                    val keyValue = hashMapCollection[genreId]
                    keyValue?.add(item)
                } else {

                    hashMapCollection[genreId] = mutableListOf<MovieModel>(item)
                }

//                    }
//                }
            }

            for (item in hashMapCollection) {

                val newCollection = CollectionGrouping(item.key, item.value)

                groupingMoviesCollection.add(newCollection)

            }

            notifyPropertyChanged(BR.groupingMoviesCollection)

        }
    }

    @SuppressLint("UseSparseArrays")
    private fun createGrouping() {

        moviesCollection?.let {


            val hashMapCollection: HashMap<Int, MutableList<MovieModel>> = HashMap()

            for ( item in it ) {

                for (key:Int in item.genre_ids!! ) {

//                    val keyValue= hashMap.getOrPut(key, {
//                        mutableListOf<MovieModel>(item)
//                    })

//                    hashMap?.let { hashMapCollection->

                        if((hashMapCollection.containsKey(key))) {

                            val keyValue= hashMapCollection[key]
                            keyValue?.add(item)
                        }
                        else {

                            hashMapCollection[key] =mutableListOf<MovieModel>(item)
                        }

//                    }
                }
            }

            for(item in hashMapCollection) {

                val newCollection = CollectionGrouping(item.key, item.value)

                groupingMoviesCollection.add(newCollection)

            }

            notifyPropertyChanged(BR.groupingMoviesCollection)

        }

        val genresOberver = fetchMovieGenres()
            .doAfterTerminate {

            }
            .subscribe({

                it?.result?.let { genresList ->
//                    Utils.genres = genresList
                }
            },
                {
                    Log.d("fetchMovieGenres", it?.message)
                },
                {

                })

    }
}