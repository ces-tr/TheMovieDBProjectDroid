package com.cestr.themoviedb.di.module

import android.support.v7.app.AppCompatActivity
import com.cestr.themoviedb.views.main.MainActivity
import com.cestr.themoviedb.views.moviecollectiongrouping.MovieCollectionGroupingActivity
import com.cestr.themoviedb.views.moviedetail.MovieDetailActivity
import com.cestr.themoviedb.views.moviedetail.MovieDetailCollapsingToolbarActivity
import dagger.Binds
import dagger.Module

@Module(includes = [ BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): AppCompatActivity

}

@Module(includes = [ BaseActivityModule::class])
abstract class MovieCollectionGroupingActivityModule {

    @Binds
    abstract fun bindActivity(activity: MovieCollectionGroupingActivity): AppCompatActivity

}

@Module(includes = [ BaseActivityModule::class])
abstract class MovieDetailActivityModule {

    @Binds
    abstract fun bindActivity(activity: MovieDetailActivity): AppCompatActivity
}

@Module(includes = [ BaseActivityModule::class])
abstract class MovieDetailCollapsingToolbarActivityModule {

    @Binds
    abstract fun bindActivity(activity: MovieDetailCollapsingToolbarActivity): AppCompatActivity
}

//