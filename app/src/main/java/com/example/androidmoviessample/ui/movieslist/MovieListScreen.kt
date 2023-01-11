package com.example.androidmoviessample.ui.movieslist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.androidmoviessample.R
import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.ui.error.ErrorScreenContent
import com.example.androidmoviessample.ui.theme.AndroidMoviesSampleTheme
import com.example.androidmoviessample.ui.utils.ImageEmpty
import com.example.androidmoviessample.ui.utils.ImageError
import com.example.androidmoviessample.ui.utils.ImageLoading
import java.util.*

@Composable
fun MovieListScreen(
    navController: NavHostController,
    viewModel: MovieListViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.onNavigate.collect {
            navController.navigate(it.route)
        }
    }

    MoviesListContent(movies = emptyList()) {}

    when (val state = viewModel.onState.collectAsState().value) {
        is MovieListState.ShowError -> ErrorScreenContent(
            message = state.error,
            stringResource(id = R.string.refresh)
        ) {
            viewModel.onRefreshClick()
        }
        MovieListState.ShowLoad -> MoviesListContent(
            emptyList(),
            true
        ) { }
        is MovieListState.UpdateMovieList -> MoviesListContent(
            state.movies,
            false
        )
        { viewModel.onMovieClick(it) }
    }
}

@Composable
private fun MoviesListContent(
    movies: List<Movie>,
    showProgress: Boolean = false,
    onMovieClicked: (movie: Movie) -> Unit
) {
    val showProgressState = rememberSaveable { showProgress }

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        if (showProgressState) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        LazyColumn(Modifier) {
            items(movies) {
                MovieListItem(movie = it, onMovieClicked = onMovieClicked)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieListItem(movie: Movie, onMovieClicked: (movie: Movie) -> Unit) {
    Card(
        modifier = Modifier.padding(24.dp, 8.dp, 24.dp, 0.dp),
        onClick = { onMovieClicked(movie) }) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = movie.title,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.releaseDate,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            val imageModifier = Modifier
                .fillMaxHeight()
                .widthIn(80.dp)


            movie.posterSmallPath
                ?.let {
                    SubcomposeAsyncImage(
                        modifier = imageModifier,
                        model = it,
                        alignment = Alignment.CenterEnd,
                        contentDescription = null,
                        loading = { ImageLoading(imageModifier) },
                        error = { ImageError(imageModifier) }
                    )
                }
                ?: ImageEmpty(modifier = imageModifier)
        }
    }
}


private fun getMoviesSample(): List<Movie> =
    listOf(
        Movie(
            id = UUID.randomUUID().toString(),
            posterSmallPath = null,
            posterOriginalPath = null,
            adult = false,
            overview = "",
            releaseDate = "2022",
            genreIds = listOf(),
            originalTitle = "",
            originalLanguage = "",
            title = "Test",
            backDropPath = null,
            popularity = 0.0f,
            voteCount = 0,
            video = false,
        ),
        Movie(
            id = UUID.randomUUID().toString(),
            posterSmallPath = null,
            posterOriginalPath = null,
            adult = false,
            overview = "",
            releaseDate = "2023",
            genreIds = listOf(),
            originalTitle = "",
            originalLanguage = "",
            title = "Test 2",
            backDropPath = null,
            popularity = 0.0f,
            voteCount = 0,
            video = false,
        )
    )

@Composable
@Preview
private fun PreviewMovieListItem() {
    AndroidMoviesSampleTheme {
        MovieListItem(
            movie = getMoviesSample().first()
        ) {}
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewMovieListItemNight() {
    AndroidMoviesSampleTheme {
        MovieListItem(
            movie = getMoviesSample().first()
        ) {}
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewMoviesListNight() {
    AndroidMoviesSampleTheme {
        MoviesListContent(
            movies = getMoviesSample()
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMoviesList() {
    AndroidMoviesSampleTheme {
        MoviesListContent(
            movies = getMoviesSample()
        ) {}
    }
}



