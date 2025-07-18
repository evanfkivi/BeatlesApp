package com.example.beatlesapp.ui.theme

import android.provider.MediaStore.Audio.Albums
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.beatlesapp.data.AlbumList

@Composable
fun BeatlesApp () {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination

    Scaffold() {innerPadding ->
        NavHost(navController = navController,
            startDestination = Routes.Start.route) {

            composable(route = Routes.Start.route) {
                AlbumsScreen(albumList = AlbumList,
                    onAbbeyClicked = {navController.navigate(Routes.Abbey.route)},
                    onRubberClicked = {navController.navigate(Routes.Rubber.route)},
                    onRevolverClicked = {navController.navigate(Routes.Revolver.route)},
                    onPepperClicked = {navController.navigate(Routes.Pepper.route)})
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