package com.example.androidmoviessample.domain.usecases

import com.example.androidmoviessample.domain.models.MovieDetails
import com.example.androidmoviessample.domain.repositories.IMovieRepository

class GetMovieDetailsUseCase(
    private val moviesRepository: IMovieRepository
) {
    suspend operator fun invoke(id: String): Result<MovieDetails?> =
        moviesRepository.getMovieDetails(id)
}