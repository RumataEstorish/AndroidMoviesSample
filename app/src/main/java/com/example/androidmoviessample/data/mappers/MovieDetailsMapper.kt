package com.example.androidmoviessample.data.mappers

import com.example.androidmoviessample.data.web.models.MovieDetailsResponse
import com.example.androidmoviessample.domain.models.MovieDetails

internal fun MovieDetailsResponse.toDomain(): MovieDetails =
    MovieDetails(
        id = id.toString(),
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollection,
        budget = budget,
        homePage = homePage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterOriginalPath = posterOriginalPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )