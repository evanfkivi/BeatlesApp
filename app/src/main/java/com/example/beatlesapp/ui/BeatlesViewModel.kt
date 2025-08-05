package com.example.beatlesapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.beatlesapp.data.AlbumItem
import com.example.beatlesapp.data.AlbumList
import com.example.beatlesapp.ui.screens.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BeatlesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AlbumList)
    val state: StateFlow<List<AlbumItem>> = _uiState.asStateFlow()
}

class InfoScreenViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route: Routes.Info = savedStateHandle.toRoute()
    val album: AlbumItem = AlbumList[route.index]
}
