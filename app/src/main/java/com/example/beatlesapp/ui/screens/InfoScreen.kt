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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
        BeatlesAppBar(
            title = "Album Info",
            showBackButton = true,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (data) {
            is InfoUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is InfoUiState.Success -> InfoUiStateSuccess(data = data)
            is InfoUiState.Error -> ErrorScreen(data.message, retryAction, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun InfoUiStateSuccess(data: InfoUiState.Success) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        val coverUrl = data.album?.coverArtUrl
        if (coverUrl != null) {
            AsyncImage(
                model = coverUrl,
                contentDescription = "${data.album.title} cover",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            "This album is titled ${data.details?.title}." +
                    " It was first released on ${data.details?.date}."
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Tracklist:",
            fontSize = 25.sp
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
                        track = tracks[track]
                    )
                }
            }
        }
    }
}

@Composable
fun ShowTrackItem(
    track: Track
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
                text = "${track.title}\n${
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
