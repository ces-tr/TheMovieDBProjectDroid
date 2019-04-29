package com.cestr.themoviedb.di.annotations.mapkeys

import com.cestr.themoviedb.viewmodels.base.ActivityBaseViewModel

import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ActivityViewModelKey(val value: KClass<out ActivityBaseViewModel>)