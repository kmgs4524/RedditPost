package com.york.android.redditpost.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.york.android.redditpost.R
import com.york.android.redditpost.databinding.ActivityPostDetailBinding
import com.york.android.redditpost.utils.WebViewProgressDecorator
import com.york.android.redditpost.utils.WebViewUrlLoadingDecorator

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postUrl = intent.extras?.get(POST_URL) as String? ?: return
        val binding = DataBindingUtil.setContentView<ActivityPostDetailBinding>(this, R.layout.activity_post_detail)
        WebViewProgressDecorator(WebViewUrlLoadingDecorator(binding.postWeb))
            .run { loadUrl(postUrl) }
    }

    companion object {
        private const val POST_URL = "POST_URL"

        fun newIntent(context: Context, postUrl: String): Intent {
            return Intent(context, PostDetailActivity::class.java)
                .putExtra(POST_URL, postUrl)
        }
    }
}