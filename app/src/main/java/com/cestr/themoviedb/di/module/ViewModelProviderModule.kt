package com.cestr.themoviedb.di.module

import android.arch.lifecycle.ViewModelProvider
import com.cestr.themoviedb.di.annotations.scopes.ActivityScope
import com.cestr.themoviedb.viewmodels.factory.ActivityViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderModule {

    @Binds
    @ActivityScope
    abstract fun bindViewModelFactory(viewModelFactory: ActivityViewModelFactory): ViewModelProvider.Factory
}