package com.example.beatlesapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.beatlesapp.data.AlbumItem
import com.example.beatlesapp.data.AlbumList
import com.example.beatlesapp.ui.screens.Routes

class InfoScreenViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route: Routes.Info = savedStateHandle.toRoute()
    val album: AlbumItem = AlbumList[route.index]
}
