package com.york.android.redditpost.model

import com.york.android.redditpost.api.GlobalJson
import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.api.RedditPostListService
import io.reactivex.Observable
import io.reactivex.Single

class RemotePostDataSource(private val postListService: RedditPostListService) {

    fun getFirstPosts(limit: Int): Single<List<PostEntity>> =
        postListService.getFirstPosts(limit).map {
            it.data.children.map {
                it.postEntity.apply {
                    // imageUrl 以 preview 第一張圖的 url 為主，如果每個 url 都是 null 則是空字串
                    imageUrl = if (preview == null) {
                        ""
                    } else {
                        preview.images.find { it.source.url != null }
                            ?.source?.url ?: ""
                    }
                }
            }
        }

    fun getMorePosts(limit: Int, lastPostName: String): Single<List<PostEntity>> =
        postListService.getMorePosts(limit, lastPostName).map {
            it.data.children.map {
                it.postEntity.apply {
                    // imageUrl 以 preview 第一張圖的 url 為主，如果每個 url 都是 null 則是空字串
                    imageUrl = if (preview == null) {
                        ""
                    } else {
                        preview.images.find { it.source.url != null }
                            ?.source?.url ?: ""
                    }
                }
            }
        }
}