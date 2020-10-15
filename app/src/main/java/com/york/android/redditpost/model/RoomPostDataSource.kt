package com.york.android.redditpost.model

import com.york.android.redditpost.api.PostEntity
import io.reactivex.Maybe

class RoomPostDataSource(private val postDao: PostDao) {

    fun saveOrUpdatePost(posts: List<PostEntity>): Maybe<Unit> =
        Maybe.fromCallable {
            postDao.savePostEntity(posts)
            Unit
        }

    fun getPosts(count: Int, offset: Int): Maybe<List<PostEntity>> =
        postDao.getPostEntity(count, offset)
}