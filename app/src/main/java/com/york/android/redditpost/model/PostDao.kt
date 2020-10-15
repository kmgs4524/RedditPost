package com.york.android.redditpost.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.york.android.redditpost.api.PostEntity
import io.reactivex.Maybe

@Dao
interface PostDao {

    // data 排序以插入時間為準，先插入的在前面
    @Insert(onConflict = REPLACE)
    fun savePostEntity(postEntity: List<PostEntity>): LongArray

    @Update
    fun updatePostEntity(postEntity: List<PostEntity>): Maybe<Int>

    // 不足 10 筆的情況下，剩幾筆就回傳幾筆
    @Query("SELECT * FROM post LIMIT 10 OFFSET :offset")
    fun getPostEntity(offset: Int): Maybe<List<PostEntity>>
}