package com.york.android.redditpost.utils

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.lang.Exception

/**
 * WebView Decorator：強制使用WebView載入網頁
 */
class WebViewUrlLoadingDecorator(
    override val next: WebViewComponent?
) : WebViewComponent {

    init {
        setUpWebViewClient()
    }

    private fun setUpWebViewClient() {
        if (next == null) return
        when (next) {
            is WebView -> {
                next.webViewClient = DefaultWebViewClient()
            }
            else -> setUpWebViewClient()
        }
    }

    override fun loadUrl(url: String) {
        next?.loadUrl(url)
    }
}

class DefaultWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()
        return if (view == null || url == null ) {
            super.shouldOverrideUrlLoading(view, request)
            false
        } else {
            view.loadUrl(url)
            true
        }
    }
}