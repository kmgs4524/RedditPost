package com.york.android.redditpost.ui

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.model.PostRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PostPagingDataSource(
    private val postRepository: PostRepository,
    private val disposable: CompositeDisposable
) : ItemKeyedDataSource<String, PostEntity>() {

    private var totalCount = 0

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<PostEntity>
    ) {
        disposable.add(
            postRepository.loadInitialPosts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onResult(it)
                totalCount += it.size
            }, {
                Timber.d("Error $it")
                it.printStackTrace()
            })
        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<PostEntity>) {
        disposable.add(
            postRepository.loadMorePosts(params.key, totalCount)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it)
                    totalCount += it.size
                }, {
                    Timber.d("Error $it")
                    it.printStackTrace()
                })
        )
    }

    override fun getKey(item: PostEntity): String {
        return item.name
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<PostEntity>) {}
}

class PostPagingDataSourceFactory(
    private val postRepository: PostRepository,
    private val disposable: CompositeDisposable
) : DataSource.Factory<String, PostEntity>() {

    override fun create(): DataSource<String, PostEntity> {
        return PostPagingDataSource(postRepository, disposable)
    }
}