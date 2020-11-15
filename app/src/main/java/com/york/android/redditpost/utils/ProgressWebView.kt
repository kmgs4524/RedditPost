package com.york.android.redditpost.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import com.york.android.redditpost.R
import java.util.jar.Attributes

class ProgressWebView(
    context: Context,
    attrs: AttributeSet?
) : WebView(context, attrs), WebViewComponent {

    override val next: WebViewComponent? = null

    private lateinit var progressBar: ProgressBar

    init {
        initProgress()
    }

    private fun initProgress() {
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            scaleY = resources.getDimension(R.dimen.webView_progress_bar_indicator_size)
            val paddingTop = resources.getDimension(R.dimen.webView_progress_bar_padding_top)
            setPadding(0, paddingTop.toInt(), 0, 0)
        }
        addView(progressBar)
    }

    fun setProgress(progress: Int) {
        progressBar.progress = progress

        if (progress == 100) {
            progressBar.visibility = View.GONE
        }
    }
}