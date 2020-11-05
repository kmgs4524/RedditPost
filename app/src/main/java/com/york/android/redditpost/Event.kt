package com.york.android.redditpost

class Event<T>(private val value: T) {

    private var isUsed = false

    fun getContentIfNeed(): T? {
        return if (isUsed) {
            null
        } else {
            isUsed = true
            value
        }
    }

    fun peekContent(): T = value
}