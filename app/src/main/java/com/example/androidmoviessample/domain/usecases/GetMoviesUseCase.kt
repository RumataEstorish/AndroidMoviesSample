package com.example.androidmoviessample.domain.usecases

import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.repositories.IMovieRepository

class GetMoviesUseCase(
    private val moviesRepository: IMovieRepository
) {
    suspend operator fun invoke(trendingPeriod: TrendingPeriod): Result<List<Movie>> =
        moviesRepository.getMoviesList(trendingPeriod)
}
