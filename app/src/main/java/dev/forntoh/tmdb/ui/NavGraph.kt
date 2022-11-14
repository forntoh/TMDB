package dev.forntoh.tmdb.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.forntoh.tmdb.ui.MainDestinations.MOVIE_ID
import dev.forntoh.tmdb.ui.screens.discover.DiscoverMovies
import dev.forntoh.tmdb.ui.screens.movie.MovieDetails

/**
 * Destinations used in the ([TMDBApp]).
 */
object MainDestinations {
    const val DISCOVER_MOVIES_ROUTE = "discover_movies"
    const val MOVIE_DETAILS_ROUTE = "movie_details"
    const val MOVIE_ID = "movieId"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.DISCOVER_MOVIES_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.DISCOVER_MOVIES_ROUTE) { from ->
            DiscoverMovies(
                discoverMoviesViewModel = hiltViewModel(from),
                onMovieSelected = { id ->
                    from.navigateNonDuplicate {
                        navController.navigate("${MainDestinations.MOVIE_DETAILS_ROUTE}/${id}")
                    }
                },
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
        composable(
            "${MainDestinations.MOVIE_DETAILS_ROUTE}/{$MOVIE_ID}",
            arguments = listOf(
                navArgument(MOVIE_ID) { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val movieId = arguments.getInt(MOVIE_ID)

            MovieDetails(
                movieId = movieId,
                movieDetailsViewModel = hiltViewModel(backStackEntry),
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}

fun NavBackStackEntry.navigateNonDuplicate(task: () -> Unit) {
    if (lifecycleIsResumed()) task()
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED