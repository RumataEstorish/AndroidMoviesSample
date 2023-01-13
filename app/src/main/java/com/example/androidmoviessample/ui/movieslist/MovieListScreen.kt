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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.androidmoviessample.R
import com.example.androidmoviessample.domain.models.Movie
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.ui.error.ErrorScreenContent
import com.example.androidmoviessample.ui.theme.AndroidMoviesSampleTheme
import com.example.androidmoviessample.ui.utils.ImageEmpty
import com.example.androidmoviessample.ui.utils.ImageError
import com.example.androidmoviessample.ui.utils.ImageLoading
import com.example.androidmoviessample.ui.utils.TopAppBarTitle
import java.time.LocalDate
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

    when (val state = viewModel.onState.collectAsState().value) {
        is MovieListState.ShowNetworkError -> ErrorScreenContent(
            message = stringResource(id = R.string.network_error),
            actionButtonText = stringResource(id = R.string.refresh),
            onActionClick = viewModel::onRefreshClick
        )
        is MovieListState.ShowGeneralError -> ErrorScreenContent(
            message = stringResource(id = R.string.unknown_error),
            stringResource(id = R.string.refresh),
            onActionClick = viewModel::onRefreshClick
        )
        is MovieListState.ShowLoad -> MoviesListContent(
            movies = emptyList(),
            showProgress = true,
            trendingPeriod = state.trendingPeriod,
            onChangeTrendingPeriod = viewModel::onChangeTrendingPeriod
        ) { }
        is MovieListState.UpdateMovieList -> MoviesListContent(
            movies = state.movies,
            showProgress = false,
            trendingPeriod = state.trendingPeriod,
            onMovieClicked = viewModel::onMovieClick,
            onChangeTrendingPeriod = viewModel::onChangeTrendingPeriod
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoviesListContent(
    movies: List<Movie>,
    showProgress: Boolean = false,
    trendingPeriod: TrendingPeriod,
    onChangeTrendingPeriod: (trendingPeriod: TrendingPeriod) -> Unit,
    onMovieClicked: (movie: Movie) -> Unit
) {
    val showProgressState = rememberSaveable { showProgress }
    val trendingPeriodState = rememberSaveable { trendingPeriod }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopAppBarTitle(
                        stringResource(
                            id = when (trendingPeriodState) {
                                TrendingPeriod.DAY -> R.string.trending_movies_day
                                TrendingPeriod.WEEK -> R.string.trending_movies_week
                            }
                        )
                    )
                },
                actions = {
                    val iconButtonModifier = Modifier
                        .width(48.dp)
                        .height(48.dp)

                    val iconModifier = Modifier
                        .padding(8.dp)

                    when (trendingPeriodState) {
                        TrendingPeriod.DAY -> IconButton(
                            modifier = iconButtonModifier,
                            onClick = { onChangeTrendingPeriod(TrendingPeriod.WEEK) }
                        ) {
                            Icon(
                                modifier = iconModifier,
                                painter = painterResource(id = R.drawable.ic_trending_day),
                                contentDescription = stringResource(id = R.string.trending_week)
                            )
                        }
                        TrendingPeriod.WEEK ->
                            IconButton(
                                modifier = iconButtonModifier,
                                onClick = { onChangeTrendingPeriod(TrendingPeriod.DAY) }
                            ) {
                                Icon(
                                    modifier = iconModifier,
                                    painter = painterResource(id = R.drawable.ic_trending_week),
                                    contentDescription = stringResource(id = R.string.trending_day)
                                )
                            }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieListItem(movie: Movie, onMovieClicked: (movie: Movie) -> Unit) {
    Card(
        modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 4.dp),
        onClick = { onMovieClicked(movie) }
    ) {
        Row(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val imageModifier = Modifier
                .fillMaxHeight()
                .widthIn(80.dp)

            movie.posterSmallPath
                ?.let {
                    SubcomposeAsyncImage(
                        modifier = imageModifier,
                        model = it,
                        alignment = Alignment.CenterStart,
                        contentDescription = null,
                        loading = { ImageLoading(imageModifier) },
                        error = { ImageError(imageModifier) }
                    )
                }
                ?: ImageEmpty(modifier = imageModifier)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                Column {
                    Text(
                        text = movie.title,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = movie.overview,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        maxLines = 3,
                        lineHeight = MaterialTheme.typography.labelMedium.lineHeight,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(0.dp, 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
                Text(
                    text = movie.releaseDate.year.toString(),
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }
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
            overview = "This is a good movie with very large many text to display I suppose this should move to next line and ellipsize at the end",
            releaseDate = LocalDate.now(),
            genreIds = listOf(),
            originalTitle = "",
            originalLanguage = "",
            title = "Test",
            backDropPath = null,
            popularity = 0.0f,
            voteCount = 0,
            video = false
        ),
        Movie(
            id = UUID.randomUUID().toString(),
            posterSmallPath = null,
            posterOriginalPath = null,
            adult = false,
            overview = "This is a good movie",
            releaseDate = LocalDate.now(),
            genreIds = listOf(),
            originalTitle = "",
            originalLanguage = "",
            title = "Test 2",
            backDropPath = null,
            popularity = 0.0f,
            voteCount = 0,
            video = false
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
            movies = getMoviesSample(),
            trendingPeriod = TrendingPeriod.WEEK,
            onChangeTrendingPeriod = {}
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMoviesList() {
    AndroidMoviesSampleTheme {
        MoviesListContent(
            movies = getMoviesSample(),
            trendingPeriod = TrendingPeriod.DAY,
            onChangeTrendingPeriod = {}
        ) {}
    }
}
