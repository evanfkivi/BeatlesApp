package com.example.beatlesapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.beatlesapp.data.Album
import com.example.beatlesapp.data.BeatlesApplication
import com.example.beatlesapp.data.BeatlesRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BeatlesUiState {
    data class Success(val albums: List<Album>) : BeatlesUiState
    data class Error(val message: String) : BeatlesUiState
    object Loading : BeatlesUiState
}

class BeatlesViewModel(private val beatlesRepository: BeatlesRepository) : ViewModel() {

    var beatlesUiState: BeatlesUiState by mutableStateOf(BeatlesUiState.Loading)
        private set

    init {
        getAlbums()
    }

    fun getAlbums() {
        viewModelScope.launch {
            beatlesUiState = BeatlesUiState.Loading
            beatlesUiState = try {
                BeatlesUiState.Success(beatlesRepository.getAlbums())
            } catch (e: IOException) {
                BeatlesUiState.Error(e.message ?: "Unknown error")
            } catch (e: HttpException) {
                BeatlesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BeatlesApplication)
                val beatlesRepository = application.container.beatlesRepository
                BeatlesViewModel(beatlesRepository = beatlesRepository)
            }
        }
    }
}
