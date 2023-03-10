package com.example.androidmoviessample.ui.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.usecases.GetMoviesUseCase
import com.example.androidmoviessample.domain.usecases.TrendingPeriodUseCase
import com.example.androidmoviessample.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException

class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val trendingPeriodUseCase: TrendingPeriodUseCase
) : ViewModel() {

    private val _onNavigate = MutableSharedFlow<Screen>()
    val onNavigate = _onNavigate.asSharedFlow()

    private val _onState = MutableStateFlow<MovieListState>(MovieListState.ShowLoad(trendingPeriodUseCase.trendingPeriod))
    val onState = _onState.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _onState.value = MovieListState.ShowLoad(trendingPeriodUseCase.trendingPeriod)

            val result = getMoviesUseCase(trendingPeriodUseCase.trendingPeriod)
            when {
                result.isFailure -> {
                    _onState.value =
                        when (result.exceptionOrNull()) {
                            is ConnectException -> MovieListState.ShowNetworkError
                            else -> MovieListState.ShowGeneralError
                        }
                }
                result.isSuccess ->
                    _onState.value =
                        MovieListState.UpdateMovieList(result.getOrNull() ?: emptyList(), trendingPeriodUseCase.trendingPeriod)
            }
        }
    }

    fun onChangeTrendingPeriod(trendingPeriod: TrendingPeriod) {
        trendingPeriodUseCase.trendingPeriod = trendingPeriod
        loadMovies()
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
