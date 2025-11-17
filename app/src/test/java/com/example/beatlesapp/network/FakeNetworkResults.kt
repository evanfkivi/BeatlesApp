package com.example.beatlesapp.network

import coil.network.HttpException
import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.Media
import com.example.beatlesapp.model.MusicBrainzResponse
import com.example.beatlesapp.model.Release
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse
import com.example.beatlesapp.model.Track

object FakeNetworkResults {

    val fakeGetAlbums = MusicBrainzResponse(
        releaseGroups = listOf(
            Album(
                id = "id_getAlbums",
                title = "title_getAlbums",
                firstReleaseDate = "firstReleaseDate_getAlbums"
            )
        )
    )

    val fakeTrack = Track(
        id = "id_Track",
        title = "title_Track",
        length = 1
    )

    val fakeMedia = Media(
        tracks = listOf(fakeTrack)
    )

    val fakeGetReleaseDetails = ReleaseDetailsResponse(
        id = "id_getReleaseDetails",
        title = "title_getReleaseDetails",
        date = "date_getReleaseDetails",
        media = listOf(fakeMedia),
        coverArtUrl = "coverArt_getReleaseDetails"
    )

    val fakeRelease = Release(
        id = "id_Release",
        title = "title_Release",
        date = "date_Release",
        country = "country_Release",
        status = "status_Release"
    )

    val fakeGetReleaseGroupDetails = ReleaseGroupDetailsResponse(
        id = "id_GetReleaseGroupDetails",
        title = "title_GetReleaseGroupDetails",
        firstReleaseDate = "firstReleaseDate_GetReleaseGroupDetails",
        primaryType = "primaryType_GetReleaseGroupDetails",
        releases = listOf(fakeRelease)
    )
}

