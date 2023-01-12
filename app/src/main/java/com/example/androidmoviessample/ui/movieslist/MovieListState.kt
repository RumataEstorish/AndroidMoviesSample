package com.example.androidmoviessample.ui.movieslist

import com.example.androidmoviessample.domain.models.Movie

sealed class MovieListState {

    object ShowLoad : MovieListState()

    data class UpdateMovieList(
        val movies: List<Movie>
    ) : MovieListState()

    object ShowNetworkError: MovieListState()

    object ShowGeneralError: MovieListState()
}
