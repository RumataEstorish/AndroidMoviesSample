package com.example.androidmoviessample.data.repositories

import com.example.androidmoviessample.common.IDispatchersProvider
import com.example.androidmoviessample.data.mappers.toDomain
import com.example.androidmoviessample.data.web.sources.MoviesSource
import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.MovieDetails
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.repositories.IMovieRepository
import kotlinx.coroutines.withContext

internal class MoviesRepository(
    private val moviesSource: MoviesSource,
    private val dispatchersProvider: IDispatchersProvider
) : IMovieRepository {

    override suspend fun getMoviesList(trendingPeriod: TrendingPeriod): Result<List<Movie>> =
        withContext(dispatchersProvider.io) {
            moviesSource.getMoviesList(trendingPeriod)
                .map { it.map { movie -> movie.toDomain() } }
        }

    override suspend fun getMovieDetails(id: String): Result<MovieDetails?> =
        withContext(dispatchersProvider.io) {
            id.toIntOrNull()
                ?.let {
                    moviesSource
                        .getMovieDetails(it)
                        .map { movieDetails -> movieDetails?.toDomain() }
                }
                ?: Result.failure(Exception("Id cannot be converted to int"))
        }
}
