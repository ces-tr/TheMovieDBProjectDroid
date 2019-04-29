package com.cestr.themoviedb.viewmodels.base

import android.annotation.SuppressLint
import com.cestr.themoviedb.dto.MovieResponse
import com.cestr.themoviedb.dto.MovieVideoResponseWrapper
import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.model.observablemodel.MovieModelObservable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


abstract class MovieBaseViewModel(var moviesManager: IMovieManager) : ActivityBaseViewModel() {


    @SuppressLint("CheckResult")
    protected fun fetchMovieDetailData(movieId: Int, language: String?) : Observable<MovieResponse> {

           val fetchMoviesObserver= moviesManager.getMovieData(movieId.toString(),language)
                .observeOn(Schedulers.io())


        return fetchMoviesObserver!!;
    }

    protected fun fetchMovieVideos(movieId:Int): Observable<MovieVideoResponseWrapper>  {

        val fetchMoviesVideosObserver= moviesManager.getMovieVideos(movieId)
            .observeOn(Schedulers.io())


        return fetchMoviesVideosObserver
    }

}