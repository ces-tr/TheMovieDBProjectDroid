package com.cestr.themoviedb.manager

import com.cestr.themoviedb.dto.MovieGenresResponseWrapper
import com.cestr.themoviedb.dto.MovieResponse
import com.cestr.themoviedb.dto.MovieResponseWrapper
import com.cestr.themoviedb.dto.MovieVideoResponseWrapper
import com.cestr.themoviedb.retrofit.services.ITMDBApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface IMovieManager {

    fun getPopularMovies(orderBy:String?, page:Int?, language:String?) : Observable<MovieResponseWrapper>
    fun getTopRatedMovies(): Observable<MovieResponseWrapper>
    fun getMovieData(movieId:String, language: String?): Observable<MovieResponse>
    fun getMovieVideos(movieId :Int): Observable<MovieVideoResponseWrapper>
    fun getGenres() : Observable<MovieGenresResponseWrapper>
    fun getMoviesByGenre(with_genres:String) : Observable<MovieResponseWrapper>
}

class MovieManager

    @Inject constructor() : IMovieManager {

    @set:Inject
    lateinit var theMovieDBApi: ITMDBApi

    override fun getPopularMovies(orderBy:String?, page:Int?,  language:String?): Observable<MovieResponseWrapper> {

        return theMovieDBApi.getPoPularMoviesData(orderBy = orderBy, page = page, language =  language).subscribeOn(Schedulers.io())
    }

    override fun getTopRatedMovies(): Observable<MovieResponseWrapper> {

        return theMovieDBApi.getTopRatedMoviesData().subscribeOn(Schedulers.io())
    }

    override fun getMovieData(movieId:String,  language:String?): Observable<MovieResponse> {

        return theMovieDBApi.getMovieData(movieId,language).subscribeOn(Schedulers.io())
    }

    override fun getMovieVideos(movieId:Int): Observable<MovieVideoResponseWrapper> {

        return theMovieDBApi.getMovieVideos(movieId).subscribeOn(Schedulers.io())
    }

    override fun getGenres(): Observable<MovieGenresResponseWrapper> {

        return theMovieDBApi.getGenres()
    }

    override fun getMoviesByGenre(with_genres:String) : Observable<MovieResponseWrapper>{

        return theMovieDBApi.getMoviesByGenre(with_genres)
    }

}