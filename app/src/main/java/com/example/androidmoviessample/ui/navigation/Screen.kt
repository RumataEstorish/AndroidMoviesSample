package com.example.androidmoviessample.ui.navigation

sealed class Screen(val route: String) {
    companion object {
        const val MOVIE_DETAILS_ID = "MOVIE_ID"
        const val NAV_ROUTE_MOVIE_LIST = "MOVIE_LIST"
        const val NAV_ROUTE_MOVIE_DETAILS = "MOVIE_DETAILS/{$MOVIE_DETAILS_ID}"
    }

    object MovieList : Screen(NAV_ROUTE_MOVIE_LIST)
    data class MovieDetails(val movieId: String) : Screen("MOVIE_DETAILS/$movieId")
}
