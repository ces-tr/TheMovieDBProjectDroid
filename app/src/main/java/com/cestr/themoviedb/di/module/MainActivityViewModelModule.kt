package com.cestr.themoviedb.di.module

import com.cestr.themoviedb.manager.IMovieManager
import com.cestr.themoviedb.manager.MovieManager
import dagger.Binds
import dagger.Module

@Module()
abstract class MainActivityViewModelModule {


    @Binds
    abstract fun bindTheMovieDBApiServiceRetrofitImplementation(manager: MovieManager): IMovieManager
//
//    @Binds
//    abstract fun bindRetrofitSessionMAnager(manager: SessionManager): ISessionManager
//
//    @Binds
//    abstract fun bindRetrofitMemberMAnager(manager: MemberManager): IMemberManager
//
//    @Binds
//    abstract fun bindRetrofitProductManager(manager: ProductManager): IProductManager


}