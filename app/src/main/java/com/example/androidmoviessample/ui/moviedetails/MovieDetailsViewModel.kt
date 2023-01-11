package com.example.androidmoviessample.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmoviessample.domain.usecases.GetMovieDetailsUseCase
import com.example.androidmoviessample.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieId: String,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _onNavigate = MutableSharedFlow<Screen>()
    val onNavigate = _onNavigate.asSharedFlow()

    private val _onState = MutableStateFlow<MovieDetailsState>(MovieDetailsState.ShowLoad)
    val onState = _onState.asStateFlow()

    init {
        viewModelScope.launch {
            _onState.value = MovieDetailsState.ShowLoad

            val result = getMovieDetailsUseCase(movieId)
            when {
                result.isFailure -> _onState.value =
                    MovieDetailsState.ShowError(result.exceptionOrNull()?.toString() ?: "")
                result.isSuccess ->
                    result.getOrThrow()
                        ?.let {
                            _onState.value = MovieDetailsState.UpdateMovieDetails(it)
                        }
            }

        }
    }

    fun onBackpressed() {
        viewModelScope.launch {
            _onNavigate.emit(Screen.MovieList)
        }
    }

    fun onErrorScreenClick() {
        viewModelScope.launch {
            _onNavigate.emit(Screen.MovieList)
        }
    }

}