package com.example.beatlesapp.ui.theme

import android.provider.MediaStore.Audio.Albums
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beatlesapp.data.AlbumItem

@Composable
fun AlbumsScreen (albumList: List<AlbumItem>,
                  onAbbeyClicked: () -> Unit,
                  onRubberClicked: () -> Unit,
                  onRevolverClicked: () -> Unit,
                  onPepperClicked: () -> Unit) {

    Column {

        BeatlesAppBar()

        LazyVerticalGrid (columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Top){

            item {
                ShowAlbumItem(albumText = albumList[0].title,
                    onClick = onAbbeyClicked)
            }

            item {
                ShowAlbumItem(albumText = albumList[1].title,
                    onClick = onRubberClicked)
            }

            item {
                ShowAlbumItem(albumText = albumList[2].title,
                    onClick = onRevolverClicked)
            }

            item {
                ShowAlbumItem(albumText = albumList[3].title,
                    onClick = onPepperClicked)
            }
    } }


}

@Composable
fun ShowAlbumItem (
    albumText: String,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = albumText,
            fontSize = 30.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeatlesAppBar () {
    TopAppBar(
        title = {
            Text (
                "Beatles App",
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Composable
fun InfoScreen (album: AlbumItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        BeatlesAppBar()
        Spacer(modifier = Modifier.height(20.dp))
        Text("The title of the album is ${album.title}. It was released in ${album.year}. This albums lasts ${album.length} over the span of ${album.songs} songs")
    }
}

sealed class Routes(val route: String) {
    object Start: Routes("albumsScreen")
    object Abbey: Routes("abbey")
    object Rubber: Routes("rubber")
    object Revolver: Routes("revolver")
    object Pepper: Routes("pepper")
}