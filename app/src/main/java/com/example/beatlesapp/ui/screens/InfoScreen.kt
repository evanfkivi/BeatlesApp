package com.example.beatlesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.beatlesapp.model.Track

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
            is InfoUiState.Success -> InfoUiStateSuccess(
                data = data,
                onBackClick = onBackClick
            )
            is InfoUiState.Error -> ErrorScreen(data.message, retryAction, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun InfoUiStateSuccess(
    data: InfoUiState.Success,
    onBackClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "This album is entitled ${data.details?.title}." +
                    " It was first released on ${data.details?.date}."
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            val tracks = data.details?.media
                ?.flatMap { it.tracks }

            if (tracks != null) {
                items(tracks.size) { track ->
                    ShowTrackItem(
                        index = track,
                        track = tracks[track],
                        modifier = Modifier
                    )
                }
            }
        }

        Button(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}

@Composable
fun ShowTrackItem(
    index: Int,
    track: Track,
    modifier: Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier.fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = "Track ${index + 1}:\n${track.title}\n${
                    track.length?.let { formatDuration(it) } ?: "Length unknown"
                }",
                fontSize = 25.sp
            )
        }
    }
}

fun formatDuration(ms: Int): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}
