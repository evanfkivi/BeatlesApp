package com.example.beatlesapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beatlesapp.data.AlbumList
import com.example.beatlesapp.ui.screens.AlbumsScreen
import com.example.beatlesapp.ui.screens.InfoScreen
import com.example.beatlesapp.ui.screens.Routes

@Composable
fun BeatlesApp() {
    val navController: NavHostController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Start.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = Routes.Start.route) {
                AlbumsScreen(
                    albumList = AlbumList,
                    onAbbeyClicked = { navController.navigate(Routes.Abbey.route) },
                    onRubberClicked = { navController.navigate(Routes.Rubber.route) },
                    onRevolverClicked = { navController.navigate(Routes.Revolver.route) },
                    onPepperClicked = { navController.navigate(Routes.Pepper.route) })
            }

            composable(route = Routes.Abbey.route) {
                InfoScreen(album = AlbumList[0])
            }

            composable(route = Routes.Rubber.route) {
                InfoScreen(album = AlbumList[1])
            }

            composable(route = Routes.Revolver.route) {
                InfoScreen(album = AlbumList[2])
            }
        }
    }
}
