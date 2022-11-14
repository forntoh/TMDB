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
import androidx.compose.ui.tooling.preview.Preview
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
            MovieItem(movie = movie, onMovieSelected = onMovieSelect)
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable {
        onMovieSelected(movie.id)
    }) {
        Text(text = movie.title)
        Text(text = movie.releaseDate)
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        movie = Movie(
            id = 1,
            title = "Terminator 3",
            language = "English",
            releaseDate = "2022-11-10",
            longDescription = "Description"
        ),
        onMovieSelected = { }
    )
}