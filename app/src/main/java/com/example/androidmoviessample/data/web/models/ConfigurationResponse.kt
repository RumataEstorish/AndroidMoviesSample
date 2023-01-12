package com.example.androidmoviessample.data.web.models

import com.squareup.moshi.Json

internal data class ConfigurationResponse(
    @Json(name = "images")
    val images: ConfigurationResponseImages,

    @Json(name = "change_keys")
    val changeKeys: List<String>
)
