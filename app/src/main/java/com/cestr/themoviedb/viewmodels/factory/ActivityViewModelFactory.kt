package com.cestr.themoviedb.viewmodels.factory

import android.arch.lifecycle.ViewModel
import com.cestr.themoviedb.viewmodels.base.ActivityBaseViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ActivityViewModelFactory @Inject constructor( creators: Map<Class<out ActivityBaseViewModel> ,
                                                    @JvmSuppressWildcards Provider<ActivityBaseViewModel>>)
                                                    : ViewModelFactory(creators as Map<Class<out ViewModel>, Provider<ViewModel>>)