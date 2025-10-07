package com.example.beatlesapp.data

import com.example.beatlesapp.network.BeatlesApiService

interface BeatlesRepository {
    suspend fun getAlbums(): List<Album>
}

class NetworkBeatlesRepository(
    private val beatlesApiService: BeatlesApiService
) : BeatlesRepository {
    override suspend fun getAlbums(): List<Album> {
        return beatlesApiService.getAlbums().releaseGroups
    }
}
