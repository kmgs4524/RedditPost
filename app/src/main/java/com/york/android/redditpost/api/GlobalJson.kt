package com.york.android.redditpost.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GlobalJson<T>(
    val data: T,
    val kind: String
)

data class Post(
    val after: String,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val modhash: String
)

data class Children(
    @SerializedName("data")
    val postEntity: PostEntity,
    val kind: String
)

@Entity(tableName = "post")
data class PostEntity(
    @PrimaryKey
    val id: String,

    val name: String,

    @Ignore
    val preview: Preview?,

    val thumbnail: String,

    var title: String,

    val url: String,

    /**
     * preivew 第一個圖片的 url
     */
    var imageUrl: String = "",

    @ColumnInfo(defaultValue = "")
    val after: String = ""
) {
    constructor(
        id: String,
        name: String,
        title: String,
        thumbnail: String,
        imageUrl: String,
        url: String,
        after: String) : this(id, name, preview = null, title = title, thumbnail = thumbnail, imageUrl = imageUrl, after = after, url = url)
}

data class Preview(
    val enabled: Boolean,
    val images: List<Image>
)

data class Image(
    val resolutions: List<Resolution>,
    val source: Source
)

data class Resolution(
    val height: Int,
    val url: String,
    val width: Int
)

data class Source(
    val url: String?
)