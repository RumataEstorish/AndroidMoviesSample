package com.example.androidmoviessample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidmoviessample.ui.moviedetails.MovieDetailsScreen
import com.example.androidmoviessample.ui.movieslist.MovieListScreen
import com.example.androidmoviessample.ui.navigation.Screen.Companion.MOVIE_DETAILS_ID
import com.example.androidmoviessample.ui.navigation.Screen.Companion.NAV_ROUTE_MOVIE_DETAILS
import com.example.androidmoviessample.ui.navigation.Screen.Companion.NAV_ROUTE_MOVIE_LIST
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavigationHolder() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NAV_ROUTE_MOVIE_LIST
    ) {
        composable(route = NAV_ROUTE_MOVIE_LIST) {
            MovieListScreen(
                navController = navController,
                viewModel = koinViewModel()
            )
        }

        composable(
            route = NAV_ROUTE_MOVIE_DETAILS,
            arguments = listOf(navArgument(MOVIE_DETAILS_ID) { type = NavType.StringType })
        ) {
            MovieDetailsScreen(
                navController = navController,
                viewModel = koinViewModel {
                    parametersOf(it.arguments?.getString(MOVIE_DETAILS_ID))
                }
            )
        }
    }
}
