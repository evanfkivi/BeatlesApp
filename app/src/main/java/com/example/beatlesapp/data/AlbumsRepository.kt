package com.example.beatlesapp.data

import com.example.beatlesapp.network.BeatlesApiService

class BeatlesAlbumsRepository(private val api: BeatlesApiService) {

    // Get all Beatles albums (release-groups)
    suspend fun getAlbums(): List<ReleaseGroup> {
        return api.getBeatlesAlbums().releaseGroups
    }

    // Get a specific album by ID (using the albums list)
    suspend fun getAlbumById(id: String): ReleaseGroup? {
        return getAlbums().find { it.id == id }
    }

    // Later: add functions for tracks, runtime, cover art
}

