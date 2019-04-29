package com.cestr.themoviedb

import dagger.android.support.DaggerApplication
import com.cestr.themoviedb.di.component.DaggerAppComponent


class MainApplication : DaggerApplication() {

    override fun applicationInjector() = applicationInjector

    private val applicationInjector =  DaggerAppComponent.builder()
        .application(this)
        .build()
}