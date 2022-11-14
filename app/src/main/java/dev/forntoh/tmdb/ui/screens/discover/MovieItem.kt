package dev.forntoh.tmdb.ui.screens.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.forntoh.common.entities.Movie

@Composable
fun MovieList(
    moviesCollection: List<Movie>,
    onMovieSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(moviesCollection) { movie ->
            MovieItem(movie = movie, onMovieSelect = onMovieSelect)
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable {
        onMovieSelect(movie.id)
    }) {
        Text(text = movie.title)
        Text(text = movie.releaseDate)
    }
}