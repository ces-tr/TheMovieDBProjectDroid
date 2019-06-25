package com.cestr.themoviedb.viewmodels.base

import android.annotation.SuppressLint
import com.cestr.themoviedb.dto.MovieGenresResponseWrapper
import com.cestr.themoviedb.dto.MovieResponse
import com.cestr.themoviedb.dto.MovieVideoResponseWrapper
import com.cestr.themoviedb.manager.IMovieManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


abstract class MovieBaseViewModel(var moviesManager: IMovieManager) : ActivityBaseViewModel() {


    @SuppressLint("CheckResult")
    protected fun fetchMovieDetailData(movieId: Int, language: String?) : Observable<MovieResponse> {

        return moviesManager.getMovieData(movieId.toString(),language)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
    }

    protected fun fetchMovieVideos(movieId:Int): Observable<MovieVideoResponseWrapper>  {

        return moviesManager.getMovieVideos(movieId)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    protected fun fetchMovieGenres(): Observable<MovieGenresResponseWrapper>  {

        return moviesManager.getGenres()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

}