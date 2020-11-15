package com.york.android.redditpost

import androidx.lifecycle.Observer

class EventObserver<T>(
    private val onEventUnhandledContent: (T) -> Unit
) : Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNeed()?.let {
            onEventUnhandledContent.invoke(it)
        }
    }
}