package com.example.beatlesapp.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.beatlesapp.R
import com.example.beatlesapp.model.Track
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    onBackClick: () -> Unit
) {
    val viewModel: InfoScreenViewModel = viewModel(factory = InfoScreenViewModel.Factory)
    val data = viewModel.infoUiState
    val retryAction = viewModel::getAlbum
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BeatlesAppBar(scrollBehavior = scrollBehavior) }
    ) { paddingValues ->

        Spacer(modifier = Modifier.height(20.dp))

        when (data) {
            is InfoUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is InfoUiState.Success -> InfoUiStateSuccess(data = data,
                paddingValues = paddingValues)
            is InfoUiState.Error -> ErrorScreen(data.message, retryAction as () -> Unit, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun InfoUiStateSuccess(data: InfoUiState.Success,
                       paddingValues: PaddingValues) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 16.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        val coverUrl = data.album?.coverArtUrl
        item {
            if (coverUrl != null) {
                AsyncImage(
                    model = coverUrl,
                    contentDescription = "${data.album.title} cover",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.drawable.ic_launcher_foreground),
                    fallback = painterResource(R.drawable.ic_launcher_foreground)
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Text(
                "This album is titled ${data.details?.title}." +
                        " It was first released on ${formatReleaseDateSafe(data.details?.date)}."
            )
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        item {
            Text(
                "Tracklist:",
                fontSize = 25.sp
            )
        }

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
                    track.length?.let { formatDuration(it) } ?: ""
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

fun formatReleaseDateSafe(date: String?): String {
    if (date.isNullOrBlank()) return "Unknown release date"

    return try {
        when (date.length) {
            4 -> date // yyyy → just show year

            7 -> { // yyyy-MM
                val parsed = YearMonth.parse(date)
                parsed.format(
                    DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
                )
            }

            10 -> { // yyyy-MM-dd
                val parsed = LocalDate.parse(date)
                val day = parsed.dayOfMonth
                val suffix = when {
                    day in 11..13 -> "th"
                    day % 10 == 1 -> "st"
                    day % 10 == 2 -> "nd"
                    day % 10 == 3 -> "rd"
                    else -> "th"
                }

                parsed.format(
                    DateTimeFormatter.ofPattern(
                        "MMMM d'$suffix' yyyy",
                        Locale.ENGLISH
                    )
                )
            }

            else -> date
        }
    } catch (e: Exception) {
        date
    }
}
