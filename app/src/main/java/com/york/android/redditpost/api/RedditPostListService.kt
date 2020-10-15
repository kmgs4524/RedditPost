package com.york.android.redditpost.api

import com.york.android.redditpost.api.GlobalJson
import com.york.android.redditpost.api.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditPostListService {

    @GET("r/Taiwan/hot.json")
    fun getFirstPosts(@Query("limit") limit: Int): Observable<GlobalJson<Post>>

    @GET("r/Taiwan/hot.json")
    fun getMorePosts(
        @Query("limit") limit: Int,
        @Query("after") lastPostName: String
    ): Observable<GlobalJson<Post>>

}