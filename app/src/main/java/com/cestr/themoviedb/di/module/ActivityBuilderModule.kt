package com.cestr.themoviedb.di.module

import com.cestr.themoviedb.di.annotations.scopes.ActivityScope
import com.cestr.themoviedb.views.main.MainActivity
import com.cestr.themoviedb.views.moviedetail.MovieDetailActivity
import dagger.Module

import dagger.android.ContributesAndroidInjector


@Module(includes = [ ViewModelFactoryModule::class])
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MovieDetailActivityModule::class])
    @ActivityScope
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity

//    @ContributesAndroidInjector(modules = [ProductDetailActivityModule::class])//(modules = [ViewModelFactoryModule::class]) //check DaggerAppcompatActivity
//    @ActivityScope
//    abstract fun contributeProductDetailActivity(): ProductDetailActivity
}