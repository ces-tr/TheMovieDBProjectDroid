package com.cestr.themoviedb.views.base

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.cestr.themoviedb.di.annotations.qualifiers.ActivityContext
import com.cestr.themoviedb.viewmodels.base.ActivityBaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    @field:ActivityContext
    protected lateinit var viewModelProvider: ViewModelProvider

//    protected abstract val viewModelClass: Class<VM>
    abstract fun initProperties()

    fun <T : ActivityBaseViewModel> getViewModel(viewModelClass: Class<T>): T {

        return viewModelProvider.get(viewModelClass)

//        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
//            modelClass.isAssignableFrom(it.key)
//        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
//        try {
//            @Suppress("UNCHECKED_CAST")
//            return creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }

    }

}