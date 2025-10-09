package com.example.beatlesapp.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

data class Album(
    val id: String,
    val title: String,
    @Json(name = "first-release-date") val firstReleaseDate: String?,
    @Json(name = "primary-type") val primaryType: String?
)

data class MusicBrainzResponse(
    @Json(name = "release-groups")
    val releaseGroups: List<Album>
)

@Serializable
sealed class Routes {
    @Serializable
    data object Start : Routes()

    @Serializable
    data class Info(
        val index: Int,
    ) : Routes()
}
