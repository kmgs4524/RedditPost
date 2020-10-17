package com.york.android.redditpost.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.york.android.redditpost.api.RedditPostListService
import com.york.android.redditpost.api.buildWithApi
import com.york.android.redditpost.model.PostRepository
import com.york.android.redditpost.model.RedditPostDatabase
import com.york.android.redditpost.model.RemotePostDataSource
import com.york.android.redditpost.model.RoomPostDataSource
import retrofit2.Retrofit

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val postListService = Retrofit.Builder().buildWithApi(1000L)
            .create(RedditPostListService::class.java)
        val remotePostDataSource = RemotePostDataSource(postListService)
        val database = Room.databaseBuilder(
            application,
            RedditPostDatabase::class.java,
            "redditpost_db"
        ).build()
        val postDao = database.getPostDao()
        val roomPostDataSource = RoomPostDataSource(postDao)
        val postRepository = PostRepository(remotePostDataSource, roomPostDataSource)
        return PostListViewModel(
            postRepository,
            application
        ) as T
    }
}