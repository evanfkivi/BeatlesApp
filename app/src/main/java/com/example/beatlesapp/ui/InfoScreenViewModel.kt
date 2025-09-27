package com.example.beatlesapp.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.savedstate.SavedStateRegistryOwner
import com.example.beatlesapp.data.AlbumItem
import com.example.beatlesapp.data.AlbumList
import com.example.beatlesapp.data.BeatlesAlbumsRepository
import com.example.beatlesapp.data.ReleaseGroup
import com.example.beatlesapp.ui.screens.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InfoScreenViewModel(
    private val repository: BeatlesAlbumsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route: Routes.Info = savedStateHandle.toRoute()
    private val _album = MutableStateFlow<ReleaseGroup?>(null)
    val album: StateFlow<ReleaseGroup?> = _album

    fun loadAlbum(albumId: String) {
        viewModelScope.launch {
            _album.value = repository.getAlbumById(albumId)
        }
    }
}

class InfoScreenViewModelFactory(
    private val repository: BeatlesAlbumsRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(InfoScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InfoScreenViewModel(repository, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}