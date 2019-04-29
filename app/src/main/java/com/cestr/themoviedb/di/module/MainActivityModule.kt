package com.cestr.themoviedb.di.module

import android.support.v7.app.AppCompatActivity
import com.cestr.themoviedb.views.main.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [ BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): AppCompatActivity

}
