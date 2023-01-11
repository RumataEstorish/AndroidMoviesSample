package com.example.androidmoviessample.ui.moviedetails

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.androidmoviessample.R
import com.example.androidmoviessample.domain.models.MovieDetails
import com.example.androidmoviessample.ui.error.ErrorScreenContent
import com.example.androidmoviessample.ui.theme.AndroidMoviesSampleTheme
import com.example.androidmoviessample.ui.utils.ImageEmpty
import com.example.androidmoviessample.ui.utils.ImageError
import com.example.androidmoviessample.ui.utils.ImageLoading

@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    viewModel: MovieDetailsViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.onNavigate.collect {
            navController.navigate(it.route)
        }
    }

    when (val state = viewModel.onState.collectAsState().value) {
        is MovieDetailsState.ShowError -> ErrorScreenContent(
            message = state.message,
            stringResource(id = R.string.back)
        ) {
            viewModel.onErrorScreenClick()
        }
        MovieDetailsState.ShowLoad -> MovieDetailsContent(
            movieDetails = null,
            showProgress = true
        ) { viewModel.onBackpressed() }
        is MovieDetailsState.UpdateMovieDetails -> MovieDetailsContent(
            movieDetails = state.movieDetails,
            showProgress = false
        ) { viewModel.onBackpressed() }
    }
}

@Composable
private fun MovieDetailsContent(
    movieDetails: MovieDetails?,
    showProgress: Boolean,
    onBackClick: () -> Unit
) {
    val showProgressState = rememberSaveable { showProgress }

    Column(
        Modifier
            .fillMaxSize()
    ) {
        if (showProgressState) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        movieDetails?.let {
            MovieDetailsLayout(movieDetails = it)
        }
    }
}

@Composable
private fun MovieDetailsLayout(movieDetails: MovieDetails) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {

        val imageModifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 320.dp)


        movieDetails.posterOriginalPath
            ?.let {
                SubcomposeAsyncImage(
                    modifier = imageModifier,
                    model = it,
                    alignment = Alignment.TopCenter,
                    contentDescription = null,
                    loading = { ImageLoading(imageModifier) },
                    error = { ImageError(imageModifier) }
                )
            }
            ?: ImageEmpty(modifier = imageModifier)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = movieDetails.title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Text(
                text = movieDetails.releaseDate,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
            Text(
                text = movieDetails.overview,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }

    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MovieDetailsPreviewNight() {
    AndroidMoviesSampleTheme {
        MovieDetailsContent(
            movieDetails = MovieDetails(
                id = "",
                adult = false,
                backdropPath = null,
                belongsToCollection = null,
                budget = 0,
                homePage = null,
                imdbId = "",
                originalLanguage = "",
                originalTitle = "",
                overview = "This is great and cool movie with pretty long description",
                popularity = 0.0f,
                posterOriginalPath = null,
                releaseDate = "2022",
                revenue = 0,
                runtime = null,
                status = "",
                tagline = "",
                title = "Test",
                video = false,
                voteAverage = 0.0f,
                voteCount = 0
            ), showProgress = false
        ) {

        }
    }
}