package com.york.android.redditpost

import android.app.Application
import com.york.android.redditpost.di.redditPostModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RedditPostApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@RedditPostApp)
            modules(redditPostModules)
        }
    }
}