package com.york.android.redditpost.model

import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.api.RedditPostListService
import io.reactivex.Maybe

class PostRepository(
    private val remotePostDataSource: RemotePostDataSource,
    private val roomPostDataSource: RoomPostDataSource
) {
    fun loadInitialPosts(): Maybe<List<PostEntity>> {
        return roomPostDataSource.getPosts(12, 0).flatMap { databasePosts ->
            if (databasePosts.size < 12) {
                remotePostDataSource.getFirstPosts(10).flatMapMaybe {
                    roomPostDataSource.saveOrUpdatePost(it)
                }.flatMap {
                    roomPostDataSource.getPosts(12, 0)
                }
            } else {
                Maybe.just(databasePosts)
            }
        }
    }

    fun loadMorePosts(lastPostName: String, offset: Int): Maybe<List<PostEntity>> {
        return roomPostDataSource.getPosts(10, offset).flatMap { databasePosts ->
            if (databasePosts.size < 10) {
                remotePostDataSource.getMorePosts(10, lastPostName).flatMapMaybe {
                    roomPostDataSource.saveOrUpdatePost(it)
                }.flatMap {
                    roomPostDataSource.getPosts(10, offset)
                }
            } else {
                Maybe.just(databasePosts)
            }
        }
    }
}