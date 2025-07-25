package com.example.beatlesapp.ui

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
import com.example.beatlesapp.ui.screens.Routes.Abbey
import com.example.beatlesapp.ui.screens.Routes.Pepper
import com.example.beatlesapp.ui.screens.Routes.Revolver
import com.example.beatlesapp.ui.screens.Routes.Rubber

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
                    onAbbeyClicked = { navController.navigate(Abbey.route) },
                    onRubberClicked = { navController.navigate(Rubber.route) },
                    onRevolverClicked = { navController.navigate(Revolver.route) },
                    onPepperClicked = { navController.navigate(Pepper.route) }
                )
            }

            // TODO There should only be one info screen navigation destination -- but need to setup navigation
            //  arguments, which is important and sick.

            composable(route = Routes.Abbey.route) {
                InfoScreen(
                    album = AlbumList[0],
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(route = Routes.Rubber.route) {
                InfoScreen(
                    album = AlbumList[1],
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(route = Routes.Revolver.route) {
                InfoScreen(
                    album = AlbumList[2],
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(route = Routes.Pepper.route) {
                InfoScreen(
                    album = AlbumList[3],
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
