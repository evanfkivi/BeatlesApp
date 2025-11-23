package com.example.beatlesapp.network

import com.example.beatlesapp.model.MusicBrainzResponse
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse

object FakeBeatlesApiService : BeatlesApiService {

    override suspend fun getAlbums(
        artistId: String,
        type: String,
        fmt: String
    ): MusicBrainzResponse {
        return FakeNetworkResults.fakeGetAlbums
    }

    override suspend fun getReleaseDetails(
        releaseId: String,
        inc: String,
        fmt: String
    ): ReleaseDetailsResponse {
        return FakeNetworkResults.fakeGetReleaseDetails
    }

    override suspend fun getReleaseGroupDetails(releaseGroupId: String): ReleaseGroupDetailsResponse {
        return FakeNetworkResults.fakeGetReleaseGroupDetails
    }
}
