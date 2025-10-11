package com.example.beatlesapp.network

import com.example.beatlesapp.model.MusicBrainzResponse
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeatlesApiService {
    @GET("release-group")
    suspend fun getAlbums(
        @Query("artist") artistId: String = "b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
        @Query("type") type: String = "album",
        @Query("fmt") fmt: String = "json"
    ) : MusicBrainzResponse

    @GET("release/{releaseId}")
    suspend fun getReleaseDetails(
        @Path("releaseId") releaseId: String,
        @Query("inc") inc: String = "recordings+media",
        @Query("fmt") fmt: String = "json"
    ): ReleaseDetailsResponse

    @GET("release-group/{id}?inc=releases&fmt=json")
    suspend fun getReleaseGroupDetails(@Path("id") releaseGroupId: String): ReleaseGroupDetailsResponse
}
