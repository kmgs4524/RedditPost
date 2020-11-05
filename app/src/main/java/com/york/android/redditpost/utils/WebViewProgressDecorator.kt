package com.york.android.redditpost.utils

import android.webkit.WebChromeClient
import android.webkit.WebView

class WebViewProgressDecorator(
    override val next: WebViewComponent?
) : WebViewComponent {

    init {
        setUpProgress(next)
    }

    private fun setUpProgress(next: WebViewComponent?) {
        if (next == null) return

        when (next) {
            is ProgressWebView -> {
                next.webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        next.progress = newProgress
                    }
                }
            }
            else -> setUpProgress(next.next)
        }
    }

    override fun loadUrl(url: String) {
        next?.loadUrl(url)
    }
}