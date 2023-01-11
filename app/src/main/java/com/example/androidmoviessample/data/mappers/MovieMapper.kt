package com.example.androidmoviessample.data.mappers

import com.example.androidmoviessample.data.web.models.MovieResponse
import com.example.androidmoviessample.domain.models.Movie


internal fun MovieResponse.toDomain(): Movie =
    Movie(
        id = id.toString(),
        posterSmallPath = posterSmallPath,
        posterOriginalPath = posterOriginalPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backDropPath = backDropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video
    )