package com.example.androidmoviessample.data.repositories

import com.example.androidmoviessample.data.mappers.toDomain
import com.example.androidmoviessample.data.web.sources.CredentialsSource
import com.example.androidmoviessample.data.web.sources.MoviesSource
import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.MovieDetails
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.repositories.IMovieRepository

internal class MoviesRepository(
    private val moviesSource: MoviesSource,
    private val credentialsSource: CredentialsSource
) : IMovieRepository {

    override suspend fun getMoviesList(trendingPeriod: TrendingPeriod): Result<List<Movie>> =
        moviesSource.getMoviesList(trendingPeriod, credentialsSource.apiKey)
            .map { it.map { movie -> movie.toDomain() } }


    override suspend fun getMovieDetails(id: String): Result<MovieDetails?> =
        id.toIntOrNull()
            ?.let {
                moviesSource
                    .getMovieDetails(it, credentialsSource.apiKey)
                    .map { movieDetails -> movieDetails?.toDomain() }
            }
            ?: Result.failure(Exception("Id cannot be converted to int"))
}