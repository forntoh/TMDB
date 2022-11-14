/*
 * Copyright (c) 2022, Forntoh Thomas (thomasforntoh@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.forntoh.repository

import dev.forntoh.common.entities.DiscoverMoviesFilter
import dev.forntoh.common.entities.Movie
import dev.forntoh.web_service.datasources.ImdbNetworkDataSource
import dev.forntoh.web_service.dto.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository that provides Movies data to ViewModels.
 * Implementation of [MoviesRepo]
 */
class MoviesRepoImpl @Inject constructor(
    private val imdbNetworkDataSource: ImdbNetworkDataSource,
) : MoviesRepo() {

    private val totalPages = MutableStateFlow(1)

    override val searchResults: Flow<List<Movie>> = imdbNetworkDataSource.searchResultsFlow
        .map { dto ->
            dto?.let {
                it.results.map { movie -> movie.toModel() }
            } ?: emptyList()
        }.flowOn(Dispatchers.IO)

    override suspend fun searchMovies(query: String) {
        val q = query.trim()
        if (q.length > 2) imdbNetworkDataSource.searchMovies(query)
    }

    override val movies: Flow<List<Movie>> = imdbNetworkDataSource.moviesFlow
        .map { dto ->
            dto?.let {
                totalPages.emit(it.totalPages)
                it.results.map { movie -> movie.toModel() }
            } ?: emptyList()
        }.flowOn(Dispatchers.IO)

    override suspend fun updateFilters(newFilters: DiscoverMoviesFilter) = newFilters.apply {
        if (page > totalPages.value) page = totalPages.value
        else if (page <= 0) page = 1
        else imdbNetworkDataSource.fetchMovies(this)
    }

    override suspend fun loadMovieDetails(id: Int) = imdbNetworkDataSource.fetchMovieDetails(id)?.toModel()

}