package com.example.beatlesapp.network

import com.example.beatlesapp.data.NetworkBeatlesRepository
import com.example.beatlesapp.model.ReleaseGroupDetailsResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class FakeNetworkBeatlesRepositoryTest {

    private lateinit var repository: NetworkBeatlesRepository

    @Before
    fun setup() {
        repository = NetworkBeatlesRepository(FakeBeatlesApiService)
    }

    @Test
    fun getAlbums_returnsFakeAlbums() = runTest {
        val albums = repository.getAlbums()

        assertEquals(
            FakeNetworkResults.fakeGetAlbums.releaseGroups.size,
            albums.size
        )

        assertEquals(
            FakeNetworkResults.fakeGetAlbums.releaseGroups.first().title,
            albums.first().title
        )
    }

    @Test
    fun getAlbum_returnsCorrectAlbum() = runTest {
        val expected = FakeNetworkResults.fakeGetAlbums.releaseGroups[0]
        val actual = repository.getAlbum(0)

        assertEquals(expected.id, actual.id)
        assertEquals(expected.title, actual.title)
    }

    @Test
    fun getAlbumDetails_returnsAlbumAndDetails() = runTest {
        val (album, details) = repository.getAlbumDetails(0)

        val expectedAlbum = FakeNetworkResults.fakeGetAlbums.releaseGroups[0]
        assertEquals(expectedAlbum.id, album.id)

        val expectedDetails = FakeNetworkResults.fakeGetReleaseDetails
        assertNotNull(details)
        assertEquals(expectedDetails.id, details?.id)
        assertEquals(expectedDetails.title, details?.title)
    }

    @Test
    fun getAlbumDetails_handlesNullReleaseDetailsSafely() = runTest {

        val fakeNoReleases = FakeNetworkResults.fakeGetReleaseGroupDetails.copy(
            releases = null
        )

        val repo = object : FakeNetworkBeatlesRepository() {
            override suspend fun getReleaseGroupDetails(id: String): ReleaseGroupDetailsResponse {
                return fakeNoReleases
            }
        }

        val (_, details) = repo.getAlbumDetails(0)

        assertNull(details)
    }
}
