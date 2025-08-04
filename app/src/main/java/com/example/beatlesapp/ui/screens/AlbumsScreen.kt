package com.example.beatlesapp.ui.screens

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
import com.example.beatlesapp.ui.BeatlesViewModel

/**
 * TODO [albumList] should come from the an `AlbumsViewModel`.
 *
 * The lambdas can be reduced down to one `onAlbumItemClicked` and we can provide it a `AlbumItem`.
 */
@Composable
fun AlbumsScreen(
    viewModel: BeatlesViewModel,
    onItemClicked: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        BeatlesAppBar()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Top
        ) {
            item {
                ShowAlbumItem(
                    album = viewModel.state[0],
                    onClick = { onItemClicked(0) }
                )
            }

            item {
                ShowAlbumItem(
                    album = viewModel.state[1],
                    onClick = { onItemClicked(1) }
                )
            }

            item {
                ShowAlbumItem(
                    album = viewModel.state[2],
                    onClick = { onItemClicked(2) }
                )
            }

            item {
                ShowAlbumItem(
                    album = viewModel.state[3],
                    onClick = { onItemClicked(3) }
                )
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
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
    data object Info : Routes("infoScreen")
}
