package com.example.androidmoviessample.data.web.sources

import com.example.androidmoviessample.data.web.models.MovieDetailsResponse
import com.example.androidmoviessample.data.web.models.MovieResponse
import com.example.androidmoviessample.data.web.requests.IMoviesRequest
import com.example.androidmoviessample.domain.models.TrendingPeriod

internal class MoviesSource(
    private val moviesRequest: IMoviesRequest
) {

    private suspend fun getImageRealUrl(apiKey: String): Pair<String?, String?> =
        moviesRequest.getConfiguration(apiKey)
            .body()
            ?.images
            ?.let {
                it.secureBaseUrl + it.posterSizes.first() to it.secureBaseUrl + it.posterSizes.last()
            }
            ?: (null to null)

    suspend fun getMoviesList(
        trendingPeriod: TrendingPeriod,
        apiKey: String
    ): Result<List<MovieResponse>> =
        runCatching {

            val (smallPath, originPath) = getImageRealUrl(apiKey)

            moviesRequest.getTrendingMovieList(
                timeWindow = when (trendingPeriod) {
                    TrendingPeriod.DAY -> IMoviesRequest.TRENDING_DAY
                    TrendingPeriod.WEEK -> IMoviesRequest.TRENDING_WEEK
                },
                apiKey = apiKey
            )
                .body()
                ?.movies
                ?.onEach {
                    it.posterSmallPath = smallPath + it.posterPath
                    it.posterOriginalPath = originPath + it.posterPath
                }
                ?: emptyList()
        }

    suspend fun getMovieDetails(id: Int, apiKey: String): Result<MovieDetailsResponse?> =
        runCatching {
            val (_, originPath) = getImageRealUrl(apiKey)

            moviesRequest
                .getMovieDetails(id, apiKey = apiKey)
                .body()
                ?.apply { posterOriginalPath = originPath + posterPath }
        }

}