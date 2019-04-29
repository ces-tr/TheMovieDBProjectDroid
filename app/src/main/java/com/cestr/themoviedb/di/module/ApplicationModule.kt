package com.cestr.themoviedb.di.module

import android.app.Application
import android.content.Context
import com.cestr.themoviedb.MainApplication
import dagger.Binds
import dagger.Module

import javax.inject.Singleton




@Module(
    includes = [NetworkModule::class]

)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: MainApplication): Application

    @Binds
    abstract fun bindContext(application: Application): Context


}