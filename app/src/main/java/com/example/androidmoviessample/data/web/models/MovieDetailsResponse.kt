package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

internal data class MovieDetailsResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any?,
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "genres")
    val genres: List<GenreResponse>,
    @Json(name = "home_page")
    val homePage: String?,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Float,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyResponse>,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryResponse>,
    @Json(name = "release_date")
    val releaseDate: LocalDate,
    @Json(name = "revenue")
    val revenue: Long,
    @Json(name = "runtime")
    val runtime: Int?,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
) {
    var posterOriginalPath: String? = null
        internal set
}
