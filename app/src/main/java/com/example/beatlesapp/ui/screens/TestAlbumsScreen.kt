package com.example.beatlesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.beatlesapp.data.ReleaseGroup
import com.example.beatlesapp.ui.theme.AlbumsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestAlbumsScreen(viewModel: AlbumsViewModel = viewModel()) {
    val albums by viewModel.albums.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("The Beatles Albums") })
        }
    ) { padding ->
        if (albums.isEmpty()) {
            Text("No albums loaded", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(contentPadding = padding) {
                items(albums) { album ->
                    TestAlbumItem(album)
                }
            }
        }
    }
}

@Composable
fun TestAlbumItem(album: ReleaseGroup) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = album.title, style = MaterialTheme.typography.titleMedium)
        album.firstReleaseDate?.let {
            Text(text = "Released: $it", style = MaterialTheme.typography.bodySmall)
        }
    }
}