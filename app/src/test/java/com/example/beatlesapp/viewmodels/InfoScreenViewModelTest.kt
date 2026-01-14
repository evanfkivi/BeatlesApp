package com.example.beatlesapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.example.beatlesapp.model.Routes
import com.example.beatlesapp.network.FakeNetworkBeatlesRepository
import com.example.beatlesapp.network.FakeNetworkResults
import com.example.beatlesapp.ui.screens.BeatlesUiState
import com.example.beatlesapp.ui.screens.BeatlesViewModel
import com.example.beatlesapp.ui.screens.InfoScreenViewModel
import com.example.beatlesapp.ui.screens.InfoUiState
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class InfoScreenViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun InfoScreenViewModel_getAlbum_verifySuccess() =
        runTest{

            val savedState = SavedStateHandle(
                mapOf("route" to Routes.Info(0))
            )

            val repository = FakeNetworkBeatlesRepository()

            val viewModel = InfoScreenViewModel(
                beatlesRepository = repository,
                savedStateHandle = savedState
            )

            advanceUntilIdle()

            val state = viewModel.infoUiState
            assertTrue(state is InfoUiState.Success)

            val success = state as InfoUiState.Success

            assertEquals(FakeNetworkResults.fakeGetAlbums.releaseGroups[0], success.album)

            assertEquals(FakeNetworkResults.fakeGetReleaseDetails, success.details)
        }

//    @Test
//    fun InfoScreenViewModel_getAlbum_verifyError() =
//        runTest{
//            val savedState = SavedStateHandle(
//                mapOf("route" to Routes.Info(0))
//            )
//
//            val repository = FakeNetworkBeatlesRepository(
//                throwable = IOException("IOException")
//            )
//
//            val viewModel = InfoScreenViewModel(
//                beatlesRepository = repository,
//                savedStateHandle = savedState
//            )
//
//            advanceUntilIdle()
//
//            val state = viewModel.infoUiState
//
//            kotlin.test.assertTrue(state is InfoUiState.Error)
//        }
//
//    @Test
//    fun InfoScreenViewModel_getAlbum_verifyIOException() =
//        runTest{
//            val savedState = SavedStateHandle(
//                mapOf("route" to Routes.Info(0))
//            )
//
//            val repository = FakeNetworkBeatlesRepository(
//                throwable = IOException("IOException")
//            )
//
//            val viewModel = InfoScreenViewModel(
//                beatlesRepository = repository,
//                savedStateHandle = savedState
//            )
//
//            advanceUntilIdle()
//
//            val state = viewModel.infoUiState
//
//            assertEquals(
//                "IOException",
//                (state as InfoUiState.Error).message
//            )
//        }
}
