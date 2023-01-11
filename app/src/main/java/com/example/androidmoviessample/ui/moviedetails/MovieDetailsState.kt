package com.example.androidmoviessample.ui.moviedetails

import com.example.androidmoviessample.domain.models.MovieDetails

sealed class MovieDetailsState {
    object ShowLoad : MovieDetailsState()

    data class ShowError(
        val message: String
    ) : MovieDetailsState()

    data class UpdateMovieDetails(
        val movieDetails: MovieDetails
    ) : MovieDetailsState()
}
