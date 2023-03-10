package com.example.androidmoviessample.di

import com.example.androidmoviessample.domain.usecases.GetMovieDetailsUseCase
import com.example.androidmoviessample.domain.usecases.GetMoviesUseCase
import com.example.androidmoviessample.domain.usecases.TrendingPeriodUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetMoviesUseCase)

    factoryOf(::GetMovieDetailsUseCase)

    factoryOf(::TrendingPeriodUseCase)
}
