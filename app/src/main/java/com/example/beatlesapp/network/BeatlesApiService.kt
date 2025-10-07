package com.example.beatlesapp.network

import com.example.beatlesapp.data.MusicBrainzResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BeatlesApiService {
    @GET("release-group")
    suspend fun getAlbums(
        @Query("artist") artistId: String = "b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
        @Query("type") type: String = "album",
        @Query("fmt") fmt: String = "json"
    ) : MusicBrainzResponse
}
