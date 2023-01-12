package com.example.androidmoviessample.data.web.requests

import com.example.androidmoviessample.data.web.models.ConfigurationResponse
import com.example.androidmoviessample.data.web.models.MovieDetailsResponse
import com.example.androidmoviessample.data.web.models.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface IMoviesRequest {

    companion object {
        const val TRENDING_DAY = "day"
        const val TRENDING_WEEK = "week"
    }

    @GET("/3/trending/{media_type}/{time_window}")
    suspend fun getTrendingMovieList(
        @Path(value = "media_type") mediaType: String = "movie",
        @Path(value = "time_window") timeWindow: String,
        @Query("api_key") apiKey: String
    ): Response<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path(value = "movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>

    @GET("/3/configuration")
    suspend fun getConfiguration(
        @Query("api_key")
        apiKey: String
    ): Response<ConfigurationResponse>
}
