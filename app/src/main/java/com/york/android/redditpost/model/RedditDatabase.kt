package com.york.android.redditpost.model

import com.york.android.redditpost.model.PostDao

interface RedditDatabase {

    fun getPostDao(): PostDao
}