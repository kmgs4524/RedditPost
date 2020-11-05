package com.york.android.redditpost.di

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.york.android.redditpost.BuildConfig
import com.york.android.redditpost.api.RedditPostListService
import com.york.android.redditpost.model.PostRepository
import com.york.android.redditpost.model.RedditDatabase
import com.york.android.redditpost.model.RedditPostDatabase
import com.york.android.redditpost.model.RemotePostDataSource
import com.york.android.redditpost.model.RoomPostDataSource
import com.york.android.redditpost.viewmodel.PostListViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule : Module = module {
    viewModel { PostListViewModel(get(), androidApplication()) }
}

val apiModule : Module = module {
    single { GsonBuilder().create() }
    single { getOkHttpClient() }
    single<RedditPostListService> {
        getWebService(
            get(),
            get()
        )
    }
}

val databaseModule : Module = module {
    single<RedditDatabase> { Room.databaseBuilder(androidContext(), RedditPostDatabase::class.java,
        REDDIT_POST_DB
    ).build() }
    single { get<RedditDatabase>().getPostDao() }
    single { RoomPostDataSource(get()) }
}

val repositoryModule : Module = module {
    single { RemotePostDataSource(get()) }
    single { PostRepository(get(), get()) }
}

fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT_SECOND, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_SECOND, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> getWebService(okHttpClient: OkHttpClient, gson: Gson): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

val redditPostModules = listOf(
    viewModelModule,
    apiModule,
    databaseModule,
    repositoryModule
)

private const val REDDIT_POST_DB = "REDDIT_POST_DB"

private const val CONNECT_TIMEOUT_SECOND = 120L

private const val READ_TIMEOUT_SECOND = 240L