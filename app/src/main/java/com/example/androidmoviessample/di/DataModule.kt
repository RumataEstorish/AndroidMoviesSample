package com.example.androidmoviessample.di

import android.content.Context
import com.example.androidmoviessample.data.adapters.DateJsonAdapter
import com.example.androidmoviessample.data.repositories.MoviesRepository
import com.example.androidmoviessample.data.repositories.SettingsRepository
import com.example.androidmoviessample.data.web.requests.IMoviesRequest
import com.example.androidmoviessample.data.web.sources.MoviesSource
import com.example.androidmoviessample.domain.repositories.IMovieRepository
import com.example.androidmoviessample.domain.repositories.ISettingsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_MOVIE_DB_ADDRESS = "https://api.themoviedb.org/"
private const val SHARED_PREFS = "MOVIE_SAMPLE_PREFS"

val dataModule = module {
    factory<IMoviesRequest> {
        Retrofit.Builder()
            .baseUrl(BASE_MOVIE_DB_ADDRESS)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi
                        .Builder()
                        .add(DateJsonAdapter())
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(IMoviesRequest::class.java)
    }

    factoryOf(::MoviesRepository) binds arrayOf(IMovieRepository::class)

    factoryOf(::MoviesSource)

    factoryOf(::SettingsRepository) binds arrayOf(ISettingsRepository::class)

    factory { androidContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE) }
}
