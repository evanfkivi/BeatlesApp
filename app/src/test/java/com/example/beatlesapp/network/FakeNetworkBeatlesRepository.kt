package com.example.beatlesapp.network

import com.example.beatlesapp.data.BeatlesRepository
import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse

class FakeNetworkBeatlesRepository: BeatlesRepository {
    override suspend fun getAlbums(): List<Album> {
        return FakeNetworkResults.fakeGetAlbums
    }

    override suspend fun getAlbum(index : Int): Album {
        return TODO()
    }

    override suspend fun getDetails(releaseId: String): ReleaseDetailsResponse {
        return TODO()
    }

    override suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse {
        return TODO()
    }

    override suspend fun getAlbumDetails(index: Int): Pair<Album, ReleaseDetailsResponse?> {
        return TODO()
    }
}
