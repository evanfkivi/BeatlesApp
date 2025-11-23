package com.example.beatlesapp.network

import com.example.beatlesapp.data.BeatlesRepository
import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse

open class FakeNetworkBeatlesRepository: BeatlesRepository {
    override suspend fun getAlbums(): List<Album> {
        return FakeBeatlesApiService.getAlbums().releaseGroups
    }

    override suspend fun getAlbum(index : Int): Album {
        val albums = getAlbums()
        return albums.getOrElse(index) { throw IndexOutOfBoundsException("Album not found") }
    }

    override suspend fun getDetails(releaseId: String): ReleaseDetailsResponse {
        return FakeBeatlesApiService.getReleaseDetails(releaseId)
    }

    override suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse {
        return FakeBeatlesApiService.getReleaseGroupDetails(id)
    }

    override suspend fun getAlbumDetails(index: Int): Pair<Album, ReleaseDetailsResponse?> {
        val album = getAlbum(index)

        val releaseGroupDetails = getReleaseGroupDetails(album.id)

        val releaseId = releaseGroupDetails
            .releases
            ?.firstOrNull()
            ?.id

        val details = releaseId?.let { id ->
            try {
                getDetails(id)
            } catch (e: Exception) {
                null
            }
        }

        return Pair(album, details)
    }
}
