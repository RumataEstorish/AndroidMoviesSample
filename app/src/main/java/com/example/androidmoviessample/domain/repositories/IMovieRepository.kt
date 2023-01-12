package com.example.androidmoviessample.domain.repositories

import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.MovieDetails
import com.example.androidmoviessample.domain.models.TrendingPeriod

interface IMovieRepository {
    suspend fun getMoviesList(trendingPeriod: TrendingPeriod): Result<List<Movie>>
    suspend fun getMovieDetails(id: String): Result<MovieDetails?>
}
