package com.example.beatlesapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.beatlesapp.data.AlbumItem

/**
 * TODO [albumList] should come from the an `AlbumsViewModel`.
 *
 * The lambdas can be reduced down to one `onAlbumItemClicked` and we can provide it a `AlbumItem`.
 */
@Composable
fun AlbumsScreen(
    albumList: List<AlbumItem>,
    onAbbeyClicked: () -> Unit,
    onRubberClicked: () -> Unit,
    onRevolverClicked: () -> Unit,
    onPepperClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        BeatlesAppBar()

        LazyVerticalGrid (
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Top
        ){
            item {
                ShowAlbumItem(
                    album = albumList[0],
                    onClick = onAbbeyClicked)
            }

            item {
                ShowAlbumItem(
                    album = albumList[1],
                    onClick = onRubberClicked)
            }

            item {
                ShowAlbumItem(
                    album = albumList[2],
                    onClick = onRevolverClicked)
            }

            item {
                ShowAlbumItem(
                    album = albumList[3],
                    onClick = onPepperClicked)
            }
        }
    }
}

@Composable
fun ShowAlbumItem(
    album: AlbumItem,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(album.art),
                    contentDescription = null
                )
                Text(
                    text = album.title,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeatlesAppBar() {
    TopAppBar(
        title = {
            Text(
                "Beatles App",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

sealed class Routes(val route: String) {
    data object Start : Routes("albumsScreen")
    data object Abbey : Routes("abbey")
    data object Rubber : Routes("rubber")
    data object Revolver : Routes("revolver")
    data object Pepper : Routes("pepper")
}
