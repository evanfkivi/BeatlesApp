package com.example.beatlesapp.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.beatlesapp.data.BeatlesAlbumsRepository
import com.example.beatlesapp.data.ReleaseGroup
import com.example.beatlesapp.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val repository: BeatlesAlbumsRepository
) : ViewModel() {
    private val _albums = MutableStateFlow<List<ReleaseGroup>>(emptyList())
    val albums: StateFlow<List<ReleaseGroup>> = _albums

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val response = ApiClient.api.getBeatlesAlbums()
                Log.d("AlbumsViewModel", "API returned: ${response.releaseGroups.size} albums")
                response.releaseGroups.forEach {
                    Log.d("AlbumsViewModel", "Album: ${it.title} (${it.firstReleaseDate})")
                }
                _albums.value = response.releaseGroups
            } catch (e: Exception) {
                Log.e("AlbumsViewModel", "Error fetching albums", e)
            }
        }
    }
}

class AlbumsViewModelFactory(
    private val repository: BeatlesAlbumsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}