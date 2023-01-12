package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal data class ConfigurationResponse(
    @Json(name = "images")
    val images: ConfigurationResponseImages,

    @Json(name = "change_keys")
    val changeKeys: List<String>
)
