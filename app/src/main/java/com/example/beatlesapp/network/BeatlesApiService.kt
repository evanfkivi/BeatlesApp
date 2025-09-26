package com.example.beatlesapp.network

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import com.squareup.moshi.Json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.beatlesapp.data.ReleaseGroup
import com.example.beatlesapp.data.ReleaseGroupResponse
import com.example.beatlesapp.ui.theme.AlbumsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header(
                    "User-Agent",
                    "BeatlesApp ( evgancodking@gmail.com.com )"
                )
                .build()
            chain.proceed(request)
        }
        .build()

    val api: BeatlesApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://musicbrainz.org/ws/2/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BeatlesApiService::class.java)
    }
}

interface BeatlesApiService {
    @GET("release-group")
    suspend fun getBeatlesAlbums(
        @Query("artist") artistId: String = "b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
        @Query("type") type: String = "album",
        @Query("fmt") fmt: String = "json"
    ) : ReleaseGroupResponse
}
