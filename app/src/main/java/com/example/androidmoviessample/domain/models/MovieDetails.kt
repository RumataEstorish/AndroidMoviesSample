package com.example.androidmoviessample.domain.models

data class MovieDetails(
    val id: String,
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: Any?,
    val budget: Int,
    val homePage: String?,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterOriginalPath: String?,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
)
