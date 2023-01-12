package com.example.androidmoviessample.di

import com.example.androidmoviessample.data.adapters.DateJsonAdapter
import com.example.androidmoviessample.data.repositories.MoviesRepository
import com.example.androidmoviessample.data.web.requests.IMoviesRequest
import com.example.androidmoviessample.data.web.sources.MoviesSource
import com.example.androidmoviessample.domain.repositories.IMovieRepository
import com.squareup.moshi.Moshi
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_MOVIE_DB_ADDRESS = "https://api.themoviedb.org/"

val dataModule = module {
    factory<IMoviesRequest> {
        Retrofit.Builder()
            .baseUrl(BASE_MOVIE_DB_ADDRESS)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi
                        .Builder()
                        .add(DateJsonAdapter())
                        .build()
                )
            )
            .build()
            .create(IMoviesRequest::class.java)
    }

    factoryOf(::MoviesRepository) binds arrayOf(IMovieRepository::class)

    factoryOf(::MoviesSource)
}