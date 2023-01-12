package com.example.androidmoviessample.domain.models

import java.time.LocalDate

data class Movie(
    val id: String,
    val posterSmallPath: String?,
    val posterOriginalPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: LocalDate,
    val genreIds: List<Int>,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backDropPath: String?,
    val popularity: Float,
    val voteCount: Int,
    val video: Boolean
)
