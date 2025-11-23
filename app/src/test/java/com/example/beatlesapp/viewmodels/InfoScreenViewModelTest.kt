//package com.example.beatlesapp.viewmodels
//
//import androidx.lifecycle.SavedStateHandle
//import com.example.beatlesapp.network.FakeNetworkBeatlesRepository
//import com.example.beatlesapp.network.FakeNetworkResults
//import com.example.beatlesapp.ui.screens.InfoScreenViewModel
//import com.example.beatlesapp.ui.screens.InfoUiState
//import junit.framework.TestCase.assertTrue
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.TestDispatcher
//import kotlinx.coroutines.test.TestScope
//import kotlinx.coroutines.test.UnconfinedTestDispatcher
//import kotlinx.coroutines.test.advanceUntilIdle
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.rules.TestWatcher
//import org.junit.runner.Description
//import kotlin.test.assertEquals
//import kotlin.test.assertNotNull
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class InfoScreenViewModelTest {
//
//    @get:Rule
//    val testDispatcher = TestDispatcherRule()
//
//    @Test
//    fun InfoScreenViewModel_getAlbum_verifySuccess() =
//        runTest{
//            val viewModel = InfoScreenViewModel(
//                beatlesRepository = FakeNetworkBeatlesRepository(),
//                savedStateHandle = TODO()
//            )
//            assertEquals(
//                InfoUiState.Success(FakeNetworkResults.)
//            )
//        }
//}
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class TestDispatcherRule(
//    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
//): TestWatcher() {
//    override fun starting(description: Description) {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description) {
//        Dispatchers.resetMain()
//    }
//}
