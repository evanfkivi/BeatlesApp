package com.example.beatlesapp.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object Start : Routes()

    @Serializable
    data class Info(
        val index: Int,
    ) : Routes()
}

// getAlbums() classes
data class Album(
    val id: String,
    val title: String,
    @Json(name = "first-release-date") val firstReleaseDate: String?,
    val coverArtUrl: String? = null
)
data class MusicBrainzResponse(
    @Json(name = "release-groups")
    val releaseGroups: List<Album>
)

// getDetails() classes
data class ReleaseDetailsResponse(
    val id: String,
    val title: String,
    val date: String?,
    val media: List<Media>?,
    val coverArtUrl: String?
)
data class Media(
    val tracks: List<Track>
)
data class Track(
    val id: String,
    val title: String,
    val length: Int? // milliseconds
)

// getReleaseGroupDetails() classes
data class ReleaseGroupDetailsResponse(
    val id: String,
    val title: String,
    @Json(name = "first-release-date") val firstReleaseDate: String?,
    @Json(name = "primary-type") val primaryType: String?,
    val releases: List<Release>?
)
data class Release(
    val id: String,
    val title: String,
    val date: String?,
    val country: String?,
    val status: String?
)
