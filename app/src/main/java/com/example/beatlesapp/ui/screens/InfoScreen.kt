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
import com.example.beatlesapp.ui.InfoScreenViewModel

@Composable
fun InfoScreen(
    onBackClick: () -> Unit
) {
    val viewModel: InfoScreenViewModel = viewModel()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BeatlesAppBar()
        Spacer(modifier = Modifier.height(20.dp))
        Text("The title of the album is ${viewModel.album.title}. It was released in ${viewModel.album.year}. This album lasts ${viewModel.album.length} over the span of ${viewModel.album.songs} songs")
        Button(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}
