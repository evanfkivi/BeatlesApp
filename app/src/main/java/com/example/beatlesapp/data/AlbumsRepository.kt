package com.example.beatlesapp.data

import com.example.beatlesapp.model.Album
import com.example.beatlesapp.network.BeatlesApiService

interface BeatlesRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(index : Int): Album
}

class NetworkBeatlesRepository(
    private val beatlesApiService: BeatlesApiService
) : BeatlesRepository {
    private var albums: List<Album>? = null
    override suspend fun getAlbums(): List<Album> {
        return albums
            ?: beatlesApiService
                .getAlbums()
                .releaseGroups
                .also { albums = it }
    }

    private var album: Album? = null
    override suspend fun getAlbum(index: Int): Album {
        return album
            ?: beatlesApiService
                .getAlbums()
                .releaseGroups[index]
                .also { album = it }
    }
}
