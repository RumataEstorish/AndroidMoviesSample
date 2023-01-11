package com.example.androidmoviessample.di

import com.example.androidmoviessample.ui.moviedetails.MovieDetailsViewModel
import com.example.androidmoviessample.ui.movieslist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModules = module {

    viewModelOf(::MovieListViewModel)

    viewModel { parameters ->
        MovieDetailsViewModel(
            movieId = parameters.get(),
            getMovieDetailsUseCase = get()
        )
    }
}