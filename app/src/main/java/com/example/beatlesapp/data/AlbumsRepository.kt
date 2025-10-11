package com.example.beatlesapp.data

import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse
import com.example.beatlesapp.network.BeatlesApiService

interface BeatlesRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(index : Int): Album
//    suspend fun getAlbumById(releaseId: String): Album
    suspend fun getDetails(releaseId: String): ReleaseDetailsResponse
    suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse
}

class NetworkBeatlesRepository(
    private val beatlesApiService: BeatlesApiService
) : BeatlesRepository {

    override suspend fun getAlbums(): List<Album> {
        return beatlesApiService.getAlbums().releaseGroups
    }

    override suspend fun getAlbum(index: Int): Album {
        return beatlesApiService.getAlbums().releaseGroups[index]
    }

    override suspend fun getDetails(releaseId: String): ReleaseDetailsResponse {
        return beatlesApiService.getReleaseDetails(releaseId)
    }

    override suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse {
        return beatlesApiService.getReleaseGroupDetails(id)
    }

//    private var albums: List<Album>? = null
//    override suspend fun getAlbums(): List<Album> {
//        return albums
//            ?: beatlesApiService
//                .getAlbums()
//                .releaseGroups
//                .also { albums = it }
//    }
}
