package com.example.beatlesapp.data

import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse
import com.example.beatlesapp.network.BeatlesApiService

interface BeatlesRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(index : Int): Album
    suspend fun getDetails(releaseId: String): ReleaseDetailsResponse
    suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse
    suspend fun getAlbumDetails(index: Int): Pair<Album, ReleaseDetailsResponse?>

}

class NetworkBeatlesRepository(
    private val beatlesApiService: BeatlesApiService
) : BeatlesRepository {
    private var cachedAlbums: List<Album>? = null

    override suspend fun getAlbums(): List<Album> {
        return cachedAlbums ?: run {
            val albums = beatlesApiService
                .getAlbums()
                .releaseGroups

            cachedAlbums = albums
            albums
        }
    }

    override suspend fun getAlbum(index: Int): Album {
        val albums = cachedAlbums ?: getAlbums()
        return albums.getOrElse(index) { throw IndexOutOfBoundsException("Album not found") }
    }


    override suspend fun getDetails(releaseId: String): ReleaseDetailsResponse {
        return beatlesApiService.getReleaseDetails(releaseId)
    }

    override suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse {
        return beatlesApiService.getReleaseGroupDetails(id)
    }

    override suspend fun getAlbumDetails(index: Int): Pair<Album, ReleaseDetailsResponse?> {
        val album = getAlbum(index)

        val releaseId = album.let {
            getReleaseGroupDetails(it.id)
                .releases
                ?.firstOrNull()
                ?.id
        }

        val coverArtUrl = releaseId?.let {
            "https://coverartarchive.org/release/$it/front"
        }

        val albumWithArt = album.copy(coverArtUrl = coverArtUrl)

        val details = releaseId?.let {
            try {
                getDetails(it)
            } catch (e: Exception) {
                null
            }
        }

        return Pair(albumWithArt, details)
    }
}
