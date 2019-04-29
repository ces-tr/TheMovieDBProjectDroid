package com.cestr.themoviedb.retrofit.services

import com.cestr.themoviedb.dto.MovieResponse
import com.cestr.themoviedb.dto.MovieResponseWrapper
import com.cestr.themoviedb.dto.MovieVideoResponseWrapper
import com.cestr.themoviedb.retrofit.RetrofitServiceFactory
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITMDBApi {

    @GET("movie/popular"+ RetrofitServiceFactory.API_KEY_PARAMETER)
    fun getPoPularMoviesData(@Query("sortby") orderBy :String? = null ,
                             @Query("language") language :String? = null,
                             @Query("page") page :Int? = null
                             ): Observable<MovieResponseWrapper>


    @GET("movie/top_rated"+ RetrofitServiceFactory.API_KEY_PARAMETER)
    fun getTopRatedMoviesData(@Query("sortby") orderBy :String? = null ,
                              @Query("language") language :String? = null): Observable<MovieResponseWrapper>

    @GET("movie/{movieId}"+ RetrofitServiceFactory.API_KEY_PARAMETER)
    fun getMovieData(@Path("movieId") movieId :String,
                        @Query("language") language :String? = null ): Observable<MovieResponse>


//    http://api.themoviedb.org/3/movie/12123/videos
    @GET("movie/{movieId}/videos"+ RetrofitServiceFactory.API_KEY_PARAMETER)
    fun getMovieVideos(@Path("movieId") movieId :Int  ): Observable<MovieVideoResponseWrapper>

//    https://api.themoviedb.org/3/movie/299534?api_key=9e2b7bae9780165a5328872d2f0c9c50&language=es

//    @GET("movie/popular$API_KEY_PARAMETER")
//    fun getPopularMovies(): Call<MoviesList>
//
//    @GET("movie/top_rated$API_KEY_PARAMETER")
//    fun getTopRatedMovies(): Call<MoviesList>
//
//    @GET("movie/{movieId}/videos$API_KEY_PARAMETER")
//    fun getMovieVideos(@Path("movieId") movieId: Int): Call<VideosList>
//
//    @GET("movie/{movieId}/reviews$API_KEY_PARAMETER")
//    fun getMovieReviews(@Path("movieId") movieId: Int): Call<ReviewsList>
}

