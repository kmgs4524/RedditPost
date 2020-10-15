package com.york.android.redditpost.model

import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.model.PostDao
import io.reactivex.Maybe

class RoomPostDataSource(private val postDao: PostDao) {

    fun saveOrUpdatePost(posts: List<PostEntity>): Maybe<Unit> {
        return Maybe.fromCallable {
            postDao.savePostEntity(posts)
            Unit
        }
    }

    fun getPosts(count: Int): Maybe<List<PostEntity>> {
        return postDao.getPostEntity(count)
    }
}