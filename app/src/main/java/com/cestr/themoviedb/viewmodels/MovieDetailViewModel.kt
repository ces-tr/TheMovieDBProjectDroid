package com.cestr.themoviedb.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import com.cestr.themoviedb.BR
import com.cestr.themoviedb.dto.MovieVideoResponseWrapper
import com.cestr.themoviedb.dto.toMovieModel
import com.cestr.themoviedb.dto.toMovieVideoModel
import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.model.MovieVideoModel
import com.cestr.themoviedb.model.observablemodel.MovieModelObservable
import com.cestr.themoviedb.viewmodels.base.MovieBaseViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

open class MovieDetailViewModel @Inject constructor(moviesManager: IMovieManager) : MovieBaseViewModel(moviesManager)
{

    var movieModel: MovieModelObservable = MovieModelObservable()

    private var defaultLanguageSelected :Boolean?=true

    private val calendar = GregorianCalendar()


    @SuppressLint("CheckResult")
    fun initVM(movieId: Int, defaultLanguageSelected: Boolean) {

        this.defaultLanguageSelected= defaultLanguageSelected

        try {

            var languageParameter: String? = null
            if (!defaultLanguageSelected) {
                languageParameter = "es"
            }

            fetchMovieDetailData(movieId, languageParameter)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map { it.toMovieModel() }

                .subscribe(

                    {
                        it?.let { movieModelresponse ->

                            movieModelresponse.releaseDate?.let { date ->

                                calendar.time = date
                                this.movieModel.movieYear = calendar.get(Calendar.YEAR).toString()
                            }

                            this.movieModel.movieTitle = movieModelresponse.title

                            this.movieModel.movieRating = movieModelresponse.vote_average.toString()

                            this.movieModel.movieDuration = movieModelresponse.runtime?.toString() ?: "-"

                            this.movieModel.movieImageUrl = movieModelresponse.imgUrl!!

                            this.movieModel.movieSinopsis = movieModelresponse.sinopsis

                        }
                    },

                    {
                        Log.d("fetchMovieDetailData", "Error:" + it.message)
                    }
                )

            fetchMovieVideos(movieId).subscribeOn(Schedulers.io())
                .subscribe { onFinishedFecthingMovieVideoData(it) }

        }
        catch (e: Exception ){

        }


    }

    private fun onFinishedFecthingMovieVideoData(responseWrapper : MovieVideoResponseWrapper?){
        responseWrapper.let {

            movieVideoResponse(it!!)?.subscribe(){
                if(it!= null) {

                    movieModel.movieVideosCollection?.clear()
                    movieModel.movieVideosCollection?.addAll(it)
                    movieModel.notifyPropertyChanged(BR.movieVideosCollection)

                }
            }
        }
    }

    private fun movieVideoResponse (responseWrapper : MovieVideoResponseWrapper): Observable<MutableList<MovieVideoModel>>? {

        return Observable.fromIterable(responseWrapper.results)
            .map { it -> it.toMovieVideoModel()}
            .toList()
            .toObservable()
    }
}