package com.york.android.redditpost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
    }
}