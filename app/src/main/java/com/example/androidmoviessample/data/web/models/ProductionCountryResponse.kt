package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal data class ProductionCountryResponse(
    @Json(name = "iso_3166_1")
    val iso3166_1: String,
    @Json(name = "name")
    val name: String
)
