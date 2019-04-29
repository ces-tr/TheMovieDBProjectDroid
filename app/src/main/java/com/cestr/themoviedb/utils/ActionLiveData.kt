package com.cestr.themoviedb.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.MainThread

class ActionLiveData<T> : MutableLiveData<T>() {

    private var someActionObserver : Observer<T>? =null

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T?>) {

        // Being strict about the observer numbers is up to you
        // I thought it made sense to only allow one to handle the event
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }

        someActionObserver = Observer { data ->
            // We ignore any null values and early return
            if (data == null) {
                return@Observer
            }
            observer.onChanged(data)
            // We set the value to null straight after emitting the change to the observer
            value = null
            // This means that the state of the data will always be null / non existent
            // It will only be available to the observer in its callback and since we do not emit null values
            // the observer never receives a null value and any observers resuming do not receive the last event.
            // Therefore it only emits to the observer the single action so you are free to show messages over and over again
            // Or launch an activity/dialog or anything that should only happen once per action / click :).
        }
        super.observe(owner, someActionObserver!!)
    }

     override fun removeObserver(observer: Observer<T?>) {

        super.removeObserver(someActionObserver!!)

    }

    // Just a nicely named method that wraps setting the value
    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}