package com.example.beatlesapp.ui.screens

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.example.beatlesapp.BeatlesApplication
import com.example.beatlesapp.data.BeatlesRepository
import com.example.beatlesapp.model.Album
import com.example.beatlesapp.model.ReleaseDetailsResponse
import com.example.beatlesapp.model.Routes
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface InfoUiState {
    data class Success(
        val album: Album,
        val details: ReleaseDetailsResponse?
    ) : InfoUiState
    data class Error(val message: String) : InfoUiState
    object Loading : InfoUiState
}

class InfoScreenViewModel(
    private val beatlesRepository: BeatlesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route: Routes.Info = savedStateHandle.toRoute()
    var infoUiState: InfoUiState by mutableStateOf(InfoUiState.Loading)
        private set

    init {
        getAlbum()
    }

    fun getAlbum() {
        viewModelScope.launch {
            infoUiState = InfoUiState.Loading
            infoUiState = try {

                val album = beatlesRepository.getAlbum(route.index)
                val releaseId = beatlesRepository
                    .getReleaseGroupDetails(album.id)
                    .releases
                    ?.firstOrNull()
                    ?.id
                val details = if (releaseId != null) {
                    beatlesRepository.getDetails(releaseId)
                } else { null }

                InfoUiState.Success(
                    album,
                    details
                )

            } catch (e: IOException) {
                InfoUiState.Error(e.message ?: "Unknown error")
            } catch (e: HttpException) {
                InfoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BeatlesApplication)
                val beatlesRepository = application.container.beatlesRepository
                val savedStateHandle = createSavedStateHandle()
                InfoScreenViewModel(
                    beatlesRepository = beatlesRepository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
