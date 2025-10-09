package com.example.beatlesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InfoScreen(
    onBackClick: () -> Unit
) {
    val viewModel: InfoScreenViewModel = viewModel(factory = InfoScreenViewModel.Factory)
    val data = viewModel.infoUiState
    val retryAction = viewModel::getAlbum

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BeatlesAppBar()

        Spacer(modifier = Modifier.height(20.dp))

        when (data) {
            is InfoUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is InfoUiState.Success ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("The title of the album is ${data.album.title}. The albumID is ${data.album.id}. This album was first released on ${data.album.firstReleaseDate}.")
                    Button(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            is InfoUiState.Error -> ErrorScreen(data.message, retryAction, modifier = Modifier.fillMaxSize())
        }
    }
}
