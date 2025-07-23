package com.example.beatlesapp.ui.theme

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
import com.example.beatlesapp.data.AlbumItem

/**
 * TODO [album] should come from an `InfoViewModel`.
 *
 * The album can be provided to the `InfoViewModel` via a navigation argument.
 */
@Composable
fun InfoScreen(
    album: AlbumItem,
    onBackClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BeatlesAppBar()
        Spacer(modifier = Modifier.height(20.dp))
        Text("The title of the album is ${album.title}. It was released in ${album.year}. This album lasts ${album.length} over the span of ${album.songs} songs")
        Button(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}
