package com.cestr.themoviedb.di.module

import com.cestr.themoviedb.di.annotations.mapkeys.ActivityViewModelKey
import com.cestr.themoviedb.viewmodels.GroupingMovieListViewModel
import com.cestr.themoviedb.viewmodels.MainActivityViewModel
import com.cestr.themoviedb.viewmodels.MovieDetailViewModel
import com.cestr.themoviedb.viewmodels.base.ActivityBaseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [MainActivityViewModelModule::class])
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ActivityViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainActivityViewModel): ActivityBaseViewModel //Resolves MainActivityViewModel

    @Binds
    @IntoMap
    @ActivityViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ActivityBaseViewModel //Resolves MainActivityViewModel

    @Binds
    @IntoMap
    @ActivityViewModelKey(GroupingMovieListViewModel::class)
    abstract fun bindGroupingMovieListViewModel(viewModel: GroupingMovieListViewModel): ActivityBaseViewModel //Resolves MainActivityViewModel


    //Add more ViewModels here
}
