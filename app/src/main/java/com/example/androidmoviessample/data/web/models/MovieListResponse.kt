package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal data class MovieListResponse(
    @Json(name = "results")
    val movies: List<MovieResponse>,
    @Json(name = "page")
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)
