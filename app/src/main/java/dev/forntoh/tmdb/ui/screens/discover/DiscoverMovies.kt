package dev.forntoh.tmdb.ui.screens.discover

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import dev.forntoh.common.entities.Movie

@Composable
fun DiscoverMovies(
    discoverMoviesViewModel: DiscoverMoviesViewModel,
    onMovieSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val moviesCollection by discoverMoviesViewModel.movies.observeAsState(emptyList())

    DiscoverMovies(
        moviesCollection = moviesCollection,
        onMovieSelected = onMovieSelected,
        modifier = modifier
    )
}

@Composable
fun DiscoverMovies(
    moviesCollection: List<Movie>,
    onMovieSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    MovieList(
        moviesCollection = moviesCollection,
        onMovieSelect = onMovieSelected,
        modifier = modifier
    )
}