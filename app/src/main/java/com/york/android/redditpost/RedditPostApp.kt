package com.york.android.redditpost

import android.app.Application
import timber.log.Timber

class RedditPostApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}