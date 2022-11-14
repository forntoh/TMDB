package dev.forntoh.tmdb.ui.screens.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.forntoh.common.entities.Movie

@Composable
fun MovieDetails(
    movieId: Int,
    movieDetailsViewModel: MovieDetailsViewModel,
    modifier: Modifier = Modifier
) {

    movieDetailsViewModel.loadMovieDetails(movieId)

    val movieDetails by movieDetailsViewModel.movieDetails.collectAsState()

    movieDetails?.let {
        MovieDetails(
            movie = it,
            modifier = modifier.fillMaxWidth()
        )
    }

}

@Composable
fun MovieDetails(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.backdrop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop,
                alpha = .4f
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colors.background
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.height(200.dp)
                ) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h4,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = movie.shortDescription ?: "",
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .alpha(0.6f)
        ) {
            Text(text = "${movie.rating}", style = MaterialTheme.typography.caption)
            Text(text = movie.releaseDate, style = MaterialTheme.typography.caption)
        }

        Text(text = movie.longDescription, modifier = Modifier.padding(horizontal = 16.dp))

        movie.genres?.let { genres ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(genres) { genre ->
                    Card(
                        shape = RoundedCornerShape(100),
                    ) {
                        Text(
                            text = genre.name,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

    }
}