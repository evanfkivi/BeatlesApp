package com.example.beatlesapp.network

import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.MusicBrainzResponse
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse

class FakeBeatlesApiService : BeatlesApiService {

    override suspend fun getAlbums(): MusicBrainzResponse {
        return FakeNetworkResults.fakeGetAlbums
    }

    override suspend fun getReleaseDetails(): ReleaseDetailsResponse {
        return FakeNetworkResults.fakeGetReleaseDetails
    }

    override suspend fun getReleaseGroupDetails(): ReleaseGroupDetailsResponse {
        return FakeNetworkResults.fakeGetReleaseGroupDetails
    }
}
