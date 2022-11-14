package dev.forntoh.tmdb.ui.screens.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.forntoh.common.entities.Movie
import dev.forntoh.tmdb.utils.OnBottomReached

@Composable
fun MovieList(
    moviesCollection: List<Movie>,
    onMovieSelect: (Int) -> Unit,
    loadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 86.dp),
        state = listState,
        modifier = modifier
    ) {
        items(moviesCollection) { movie ->
            MovieItem(movie = movie, onMovieSelected = onMovieSelect)
        }
    }

    listState.OnBottomReached(buffer = 2, loadMore)
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onMovieSelected(movie.id)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.h6, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(text = movie.releaseDate, style = MaterialTheme.typography.caption)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = movie.longDescription,
                    style = MaterialTheme.typography.caption,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${movie.rating}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
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
            longDescription = "Description",
        ),
        onMovieSelected = { }
    )
}