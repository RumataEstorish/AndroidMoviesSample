package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "backdrop_path")
    val backDropPath: String?,
    @Json(name = "popularity")
    val popularity: Float,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "video")
    val video: Boolean
) {
    var posterOriginalPath: String? = null
        internal set

    var posterSmallPath: String? = null
        internal set
}
