package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json

internal data class GenreResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)
