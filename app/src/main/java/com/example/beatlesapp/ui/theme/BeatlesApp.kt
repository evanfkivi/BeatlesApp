package com.example.beatlesapp.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beatlesapp.data.AlbumList

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
                    onPepperClicked = { navController.navigate(Routes.Pepper.route) }
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
