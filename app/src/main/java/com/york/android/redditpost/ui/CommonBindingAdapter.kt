package com.york.android.redditpost.ui

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl

@BindingAdapter(value = ["imageUrl", "defaultImage"], requireAll = true)
fun setImageByUrl(imageView: ImageView, imageUrl: String?, @DrawableRes defaultImage: Int) {
    when (imageUrl) {
        null, "" ->
            Glide
                .with(imageView.context)
                .load(defaultImage)
                .into(imageView)
        else ->
            Glide
                .with(imageView.context)
                .load(GlideUrl(imageUrl))
                .error(defaultImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }
}