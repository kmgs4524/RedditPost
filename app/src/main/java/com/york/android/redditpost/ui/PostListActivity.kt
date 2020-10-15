package com.york.android.redditpost.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.york.android.redditpost.R

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

    }

    companion object {
        const val TAG = "PostListActivity"
    }
}