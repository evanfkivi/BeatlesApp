package com.example.beatlesapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beatlesapp.model.Routes
import com.example.beatlesapp.ui.screens.AlbumsScreen
import com.example.beatlesapp.ui.screens.InfoScreen

@Composable
fun BeatlesApp() {
    val navController: NavHostController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Start,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Routes.Start> {
                AlbumsScreen(
                    onItemClicked = { navController.navigate(Routes.Info(it)) }
                )
            }
            composable<Routes.Info> {
                InfoScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
