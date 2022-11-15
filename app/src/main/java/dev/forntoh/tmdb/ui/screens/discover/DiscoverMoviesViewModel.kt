package dev.forntoh.tmdb.ui.screens.discover

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forntoh.common.entities.DiscoverMoviesFilter
import dev.forntoh.repository.MoviesRepo
import dev.forntoh.tmdb.utils.getToDateString
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {

    private val today = Calendar.getInstance()

    private var filter by mutableStateOf(
        DiscoverMoviesFilter(
            sortBy = "popularity.desc",
            releasedBeforeDate = today.getToDateString(),
            year = today.get(Calendar.YEAR),
        )
    )

    /**
     * LiveData for the Movies
     */
    val movies = moviesRepo.movies.asLiveData()

    /**
     * LiveData for Movies Search Results
     */
    val searchResults = moviesRepo.searchResults.asLiveData()

    fun searchMovies(query: String) {
        viewModelScope.launch {
            moviesRepo.searchMovies(query)
        }
    }

    fun nextPage() = with(filter) {
        updateFilters(this.copy(page = page + 1))
    }

    private fun updateFilters(newFilter: DiscoverMoviesFilter) {
        viewModelScope.launch {
            filter = moviesRepo.updateFilters(newFilter)
        }
    }

}