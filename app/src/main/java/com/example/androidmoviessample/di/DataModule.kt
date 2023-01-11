package com.example.androidmoviessample.di

import com.example.androidmoviessample.data.repositories.MoviesRepository
import com.example.androidmoviessample.data.web.requests.IMoviesRequest
import com.example.androidmoviessample.data.web.sources.CredentialsSource
import com.example.androidmoviessample.data.web.sources.MoviesSource
import com.example.androidmoviessample.domain.repositories.IMovieRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    factory<IMoviesRequest> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(IMoviesRequest::class.java)
    }

    factoryOf(::MoviesRepository) binds arrayOf(IMovieRepository::class)

    factoryOf(::CredentialsSource)

    factoryOf(::MoviesSource)
}