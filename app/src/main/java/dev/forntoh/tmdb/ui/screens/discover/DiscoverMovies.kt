package dev.forntoh.tmdb.ui.screens.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.forntoh.common.entities.Movie
import dev.forntoh.tmdb.ui.components.AutoCompleteTextField

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
        loadMore = discoverMoviesViewModel::nextPage,
        modifier = modifier.statusBarsPadding()
    )
}

@Composable
fun DiscoverMovies(
    moviesCollection: List<Movie>,
    onMovieSelected: (Int) -> Unit,
    loadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    MovieList(
        moviesCollection = moviesCollection,
        onMovieSelect = onMovieSelected,
        modifier = modifier,
        loadMore = loadMore
    )

    AutoCompleteTextField(
        itemList = listOf("asds", "uiuiwui"),
        onQuery = { },
        onClearResults = { },
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp)
    )
}