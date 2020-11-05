package com.york.android.redditpost.utils

interface WebViewComponent {

    val next: WebViewComponent?

    fun loadUrl(url: String)
}