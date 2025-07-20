package com.example.beatlesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.beatlesapp.data.AlbumItem

@Composable
fun InfoScreen(album: AlbumItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BeatlesAppBar()
        Spacer(modifier = Modifier.height(20.dp))
        Text("The title of the album is ${album.title}. It was released in ${album.year}. This albums lasts ${album.length} over the span of ${album.songs} songs")
    }
}