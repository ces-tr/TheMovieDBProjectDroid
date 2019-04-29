package com.cestr.themoviedb.di.module

import android.support.v7.app.AppCompatActivity

import com.cestr.themoviedb.views.moviedetail.MovieDetailActivity
import dagger.Binds
import dagger.Module


@Module(includes = [ BaseActivityModule::class])
abstract class MovieDetailActivityModule {

    @Binds
    abstract fun bindActivity(activity: MovieDetailActivity): AppCompatActivity
}