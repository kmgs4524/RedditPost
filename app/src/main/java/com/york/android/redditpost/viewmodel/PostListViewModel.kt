package com.york.android.redditpost.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.model.PostRepository
import com.york.android.redditpost.ui.PostPagingDataSourceFactory

class PostListViewModel(
    postRepository: PostRepository,
    application: Application
) : BaseViewModel(application) {

    private val postPagingDataSourceFactory = PostPagingDataSourceFactory(postRepository, disposable)

    lateinit var posts: LiveData<PagedList<PostEntity>>

    init {
        loadPosts()
    }

    private fun loadPosts() {
        val config = PagedList.Config.Builder()
            .setPageSize(10) // 一次讀取15筆
            .setPrefetchDistance(2) // 滑到列表底部剩三筆時，開始抓新資料
            .build()
        val pageComments = LivePagedListBuilder(postPagingDataSourceFactory, config)
        posts = pageComments.build()
    }
}