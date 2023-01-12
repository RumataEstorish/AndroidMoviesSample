package com.example.androidmoviessample.ui.moviedetails

import com.example.androidmoviessample.domain.models.MovieDetails

sealed class MovieDetailsState {
    object ShowLoad : MovieDetailsState()

    object ShowNetworkError : MovieDetailsState()

    object ShowGeneralError : MovieDetailsState()

    data class UpdateMovieDetails(
        val movieDetails: MovieDetails
    ) : MovieDetailsState()
}
