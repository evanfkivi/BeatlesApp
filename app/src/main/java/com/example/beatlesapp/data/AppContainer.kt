package com.example.beatlesapp.data

import com.example.beatlesapp.network.BeatlesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val beatlesRepository: BeatlesRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://musicbrainz.org/ws/2/"

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .header(
                    "User-Agent",
                    "BeatlesAlbumsApp/1.0 ( evanfkivi@gmail.com )"
                )
                .build()
            chain.proceed(newRequest)
        }
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val beatlesApiService: BeatlesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BeatlesApiService::class.java)
    }

    override val beatlesRepository: BeatlesRepository by lazy {
        NetworkBeatlesRepository(beatlesApiService)
    }
}
