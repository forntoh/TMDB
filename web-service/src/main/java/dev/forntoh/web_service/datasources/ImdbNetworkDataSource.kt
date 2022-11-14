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

package dev.forntoh.web_service.datasources

import dev.forntoh.common.entities.DiscoverMoviesFilter
import dev.forntoh.web_service.api.ApiManager
import dev.forntoh.web_service.dto.MoviesDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [ImdbNetworkDataSource] is the data source for the IMDB Cloud API.
 * It fetches the vehicle list from the web service and updates the flow.
 *
 * @property apiManager is the api manager to fetch the vehicle list.
 */
class ImdbNetworkDataSource @Inject constructor(
    private val apiManager: ApiManager,
) {

    private val _moviesFlow: MutableStateFlow<MoviesDTO?> = MutableStateFlow(null)
    val moviesFlow = _moviesFlow as StateFlow<MoviesDTO?>

    suspend fun fetchMovies(filter: DiscoverMoviesFilter) {
        val fetchedData = apiManager.mainApi.loadMovies(
            filter.page,
            filter.sortBy,
            filter.includeAdult,
            filter.includeVideo,
            filter.releasedBeforeDate,
            filter.year
        )
        if (fetchedData.isSuccessful) _moviesFlow.emit(fetchedData.body())
    }

}