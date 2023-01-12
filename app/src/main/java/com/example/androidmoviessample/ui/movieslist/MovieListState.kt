package com.example.androidmoviessample.ui.movieslist

import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.TrendingPeriod

sealed class MovieListState {

    data class ShowLoad(
        val trendingPeriod: TrendingPeriod
    ) : MovieListState()

    data class UpdateMovieList(
        val movies: List<Movie>,
        val trendingPeriod: TrendingPeriod
    ) : MovieListState()

    object ShowNetworkError : MovieListState()

    object ShowGeneralError : MovieListState()
}
