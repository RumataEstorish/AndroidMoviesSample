package com.example.androidmoviessample.data.web.sources

import com.example.androidmoviessample.BuildConfig

internal class CredentialsSource {
    @Suppress("UNUSED_PARAMETER")
    var apiKey: String
        get() =
            if (BuildConfig.DEBUG) {
                BuildConfig.API_TEST_KEY
            } else {
                throw NotImplementedError("API key storage not implemented")
            }
        set(value) {
            throw NotImplementedError("API key storage not implemented")
        }
}