package com.york.android.redditpost.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.model.RedditDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class RedditPostDatabase : RoomDatabase(),
    RedditDatabase