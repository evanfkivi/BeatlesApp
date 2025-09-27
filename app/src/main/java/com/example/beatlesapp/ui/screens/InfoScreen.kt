package com.example.beatlesapp.ui.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.beatlesapp.data.BeatlesAlbumsRepository
import com.example.beatlesapp.network.ApiClient
import com.example.beatlesapp.ui.InfoScreenViewModel
import com.example.beatlesapp.ui.InfoScreenViewModelFactory
import com.example.beatlesapp.ui.theme.AlbumsViewModelFactory

@Composable
fun InfoScreen(
    onBackClick: () -> Unit,
    backStackEntry: NavBackStackEntry
) {
    val repository = BeatlesAlbumsRepository(ApiClient.api)

    val viewModel: InfoScreenViewModel = viewModel(factory = InfoScreenViewModelFactory(
        repository,
        backStackEntry,
        backStackEntry.arguments))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BeatlesAppBar()
        Spacer(modifier = Modifier.height(20.dp))
        Text("The value of the album is ${viewModel.album.value}")
        Button(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}
