package com.example.beatlesapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.beatlesapp.model.Routes
import com.example.beatlesapp.ui.screens.AlbumsScreen
import com.example.beatlesapp.ui.screens.BeatlesAppBar
import com.example.beatlesapp.ui.screens.InfoScreen

@OptIn(ExperimentalMaterial3Api::class)
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
                    onItemClicked = { index, title ->
                        navController.navigate(
                            Routes.Info(
                        index,
                        title
                    )) }
                )
            }
            composable<Routes.Info> { backStackEntry ->
                val args = backStackEntry.toRoute<Routes.Info>()
                InfoScreen(
                    onBackClick = { navController.popBackStack() },
                    title = args.title
                )
            }
        }
    }
}
