package com.example.androidmoviessample.ui.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.usecases.GetMoviesUseCase
import com.example.androidmoviessample.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _onNavigate = MutableSharedFlow<Screen>()
    val onNavigate = _onNavigate.asSharedFlow()

    private val _onState = MutableStateFlow<MovieListState>(MovieListState.ShowLoad)
    val onState = _onState.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _onState.value = MovieListState.ShowLoad

            val result = getMoviesUseCase(TrendingPeriod.WEEK)
            when {
                result.isFailure -> _onState.value =
                    MovieListState.ShowError(result.exceptionOrNull()?.toString() ?: "")
                result.isSuccess -> _onState.value =
                    MovieListState.UpdateMovieList(result.getOrNull() ?: emptyList())
            }
        }
    }

    fun onMovieClick(movie: Movie) {
        viewModelScope.launch {
            _onNavigate.emit(Screen.MovieDetails(movie.id))
        }
    }

    fun onRefreshClick() {
        loadMovies()
    }

}