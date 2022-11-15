package dev.forntoh.tmdb.ui.screens.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
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
    val searchResults by discoverMoviesViewModel.searchResults.observeAsState(emptyList())

    DiscoverMovies(
        moviesCollection = moviesCollection,
        searchResults = searchResults,
        onMovieSelected = onMovieSelected,
        onSearchMovies = discoverMoviesViewModel::searchMovies,
        loadMore = discoverMoviesViewModel::nextPage,
        modifier = modifier.statusBarsPadding()
    )
}

@Composable
fun DiscoverMovies(
    moviesCollection: List<Movie>,
    searchResults: List<Movie>,
    onMovieSelected: (Int) -> Unit,
    loadMore: () -> Unit,
    modifier: Modifier = Modifier,
    onSearchMovies: (String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        AutoCompleteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colors.surface),
            placeHolder = "Search",
            onQueryChanged = {
                onSearchMovies(it)
            },
            predictions = searchResults,
            onClearClick = { onSearchMovies("") },
            onDoneActionClick = { },
            onItemClick = { movie ->
                onMovieSelected(movie.id)
            }
        ) { movie ->
            MovieItem(movie = movie)
        }

        MovieList(
            moviesCollection = moviesCollection,
            onMovieSelect = onMovieSelected,
            modifier = modifier,
            loadMore = loadMore
        )
    }
}