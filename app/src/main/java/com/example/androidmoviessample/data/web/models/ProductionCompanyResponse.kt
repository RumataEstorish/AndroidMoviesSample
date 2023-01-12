package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json

internal data class ProductionCompanyResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "origin_country")
    val originCountry: String
)
