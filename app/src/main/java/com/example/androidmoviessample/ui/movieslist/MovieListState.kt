package com.example.androidmoviessample.ui.movieslist

import com.example.androidmoviessample.domain.models.Movie

sealed class MovieListState {

    object ShowLoad : MovieListState()

    data class UpdateMovieList(
        val movies: List<Movie>
    ) : MovieListState()

    data class ShowError(
        val error: String
    ) : MovieListState()
}
