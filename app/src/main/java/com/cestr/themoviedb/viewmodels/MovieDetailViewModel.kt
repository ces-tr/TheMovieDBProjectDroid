package com.cestr.themoviedb.viewmodels

import android.annotation.SuppressLint
import android.databinding.Bindable
import com.cestr.themoviedb.BR
import com.cestr.themoviedb.dto.MovieVideoResponseWrapper
import com.cestr.themoviedb.dto.toMovieModel
import com.cestr.themoviedb.dto.toMovieVideoModel
import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.model.MovieVideoModel
import com.cestr.themoviedb.model.observablemodel.MovieModelObservable
import com.cestr.themoviedb.viewmodels.base.MovieBaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

open class MovieDetailViewModel @Inject constructor(moviesManager: IMovieManager) : MovieBaseViewModel(moviesManager)
{

    var movieModel: MovieModelObservable = MovieModelObservable()


    var defaultLanguageSelected :Boolean?=true

    private val calendar = GregorianCalendar()


    @SuppressLint("CheckResult")
    fun initVM(movieId: Int, defaultLanguageSelected: Boolean) {

        this.defaultLanguageSelected= defaultLanguageSelected

        try {

            var languageParameter:String? = null
            if(!defaultLanguageSelected) {
                languageParameter = "es"
            }

            fetchMovieDetailData(movieId, languageParameter)
                        .map {it.toMovieModel()}
                        .subscribe(){

                            if(it!=null) {

                                calendar.time = it.releaseDate

                                movieModel.movieTitle = it.title

                                movieModel.movieYear = calendar.get(Calendar.YEAR).toString()

                                movieModel.movieRating = it.vote_average.toString()

                                movieModel.movieDuration = it.runtime.toString()

                                movieModel.movieImageUrl = it.imgUrl

                                movieModel.movieSinopsis = it.sinopsis


                            }
                        }

            fetchMovieVideos(movieId).subscribeOn(Schedulers.io())
                .subscribe {onFinishedFecthingMovieVideoData(it)}

//            moviesManager.getMovieData(movieId.toString())
//                            .observeOn(Schedulers.io())
//                            .map {it.toMovieModel()
//                            }
//                            .subscribeOn(Schedulers.io())
//                            .subscribe{
//
//                                it.let {
//
//                                    movieTitle = it.title
//
//                                    calendar.time = it.releaseDate
//                                    movieYear = calendar.get(Calendar.YEAR).toString()
//
//                                    movieRating = it.vote_average.toString()
//
//                                    movieDuration = it.runtime.toString()
//
//                                    movieImageUrl= it.imgUrl
//
//                                    movieSinopsis = it.sinopsis
//
//                                }
//                            }
//
        }
        catch (e: Exception ){

        }


    }

    fun onFinishedFecthingMovieVideoData(responseWrapper : MovieVideoResponseWrapper?){
        responseWrapper.let {

            movieVideoResponse(it!!)?.subscribe(){
                if(it!= null) {

                    movieModel.movieVideosCollection?.addAll(it)
                    movieModel.notifyPropertyChanged(BR.movieVideosCollection)

                }
            }
        }
    }

    fun movieVideoResponse (responseWrapper : MovieVideoResponseWrapper): Observable<MutableList<MovieVideoModel>>? {

        val response= Observable.fromIterable(responseWrapper.results)
            .map { it -> it.toMovieVideoModel()}
            .toList()
            .toObservable()

        return  response
    }
}