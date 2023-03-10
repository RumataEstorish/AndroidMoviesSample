package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SpokenLanguageResponse(
    @Json(name = "iso_639_1")
    val iso639_1: String,
    @Json(name = "name")
    val name: String
)
