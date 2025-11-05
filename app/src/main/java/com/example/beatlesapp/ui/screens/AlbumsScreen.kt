package com.example.beatlesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.beatlesapp.R
import com.example.beatlesapp.model.Album

@Composable
fun AlbumsScreen(onItemClicked: (Int) -> Unit) {
    val viewModel: BeatlesViewModel = viewModel(factory = BeatlesViewModel.Factory)
    val data = viewModel.beatlesUiState
    val retryAction = viewModel::getAlbums

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        BeatlesAppBar()

        when (data) {
            is BeatlesUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is BeatlesUiState.Success -> BeatlesUiStateSuccess(
                data = data,
                onItemClicked = onItemClicked
            )
            is BeatlesUiState.Error -> ErrorScreen(
                data.message,
                retryAction,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BeatlesUiStateSuccess(
    data: BeatlesUiState.Success,
    onItemClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(data.albums.size) { item ->
            ShowAlbumItem(
                album = data.albums[item],
                onClick = { onItemClicked(item) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ShowAlbumItem(
    album: Album,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = album.title,
                    fontSize = 25.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeatlesAppBar(
    title: String = "The Beatles",
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "loading"
    )
}

@Composable
fun ErrorScreen(
    message: String,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )
        Text(text = message, modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("retry")
        }
    }
}
