package com.cestr.themoviedb.model.observablemodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.cestr.themoviedb.BR
import com.cestr.themoviedb.model.MovieVideoModel

class MovieModelObservable : BaseObservable() {

    @Bindable
    var movieTitle : String = ""
        public set(value) {
            field = value
            notifyPropertyChanged(BR.movieTitle)
        }

    @Bindable
    var movieYear : String = ""
        public set(value) {
            field = value
            notifyPropertyChanged(BR.movieYear)
        }

    @Bindable
    var movieRating : String = ""
        public set(value) {
            field = value
            notifyPropertyChanged(BR.movieRating)
        }

    @Bindable
    var movieDuration : String=""
        public set(value) {
            field = value
            notifyPropertyChanged(BR.movieDuration)
        }

    @Bindable
    var movieSinopsis : String=""
        public set(value) {
            field = value
            notifyPropertyChanged(BR.movieSinopsis)
        }


    @Bindable
    var movieImageUrl : String=""
         public set(value) {
            field = value
            notifyPropertyChanged(BR.movieImageUrl)
        }

    @Bindable
    var movieVideosCollection: MutableList<MovieVideoModel>? =null
        public set(value) {
            field = value
            notifyPropertyChanged(BR.movieVideosCollection)
        }


    init{
        movieVideosCollection= mutableListOf<MovieVideoModel>()
    }
}