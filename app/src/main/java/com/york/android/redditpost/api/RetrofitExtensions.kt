package com.york.android.redditpost.api

import com.york.android.redditpost.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// FIXME: 2020/10/14 如果有用 Koin 的話放在 AppModule 中，並改用泛型直接create
fun Retrofit.Builder.buildWithApi(readTimeout: Long): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS).build()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}