package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GenreResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)
