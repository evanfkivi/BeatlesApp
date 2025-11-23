package com.example.beatlesapp.viewmodels

import com.example.beatlesapp.network.FakeBeatlesApiService
import com.example.beatlesapp.network.FakeNetworkBeatlesRepository
import com.example.beatlesapp.ui.screens.BeatlesUiState
import com.example.beatlesapp.ui.screens.BeatlesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BeatlesViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun BeatlesViewModel_getAlbums_verifySuccess() =
        runTest{
            val viewModel = BeatlesViewModel(
                beatlesRepository = FakeNetworkBeatlesRepository()
            )
            assertEquals(
                BeatlesUiState.Success(FakeBeatlesApiService.getAlbums().releaseGroups),
                viewModel.beatlesUiState
            )
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
