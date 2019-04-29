package com.cestr.themoviedb.di.component

import com.cestr.themoviedb.di.module.ActivityBuilderModule
import com.cestr.themoviedb.MainApplication
import com.cestr.themoviedb.di.module.ApplicationModule
import dagger.Component
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, ApplicationModule::class  ])
 interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }


}