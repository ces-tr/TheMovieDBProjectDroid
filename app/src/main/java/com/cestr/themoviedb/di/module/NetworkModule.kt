package com.cestr.themoviedb.di.module

import com.cestr.themoviedb.retrofit.RetrofitServiceFactory
import com.cestr.themoviedb.retrofit.services.ITMDBApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun provideMovieDBApiServiceRetrofitImplementation(): ITMDBApi {

        return RetrofitServiceFactory.createService(ITMDBApi::class.java)
    }



}