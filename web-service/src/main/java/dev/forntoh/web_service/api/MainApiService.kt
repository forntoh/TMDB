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

package dev.forntoh.web_service.api

import dev.forntoh.web_service.dto.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * IMDB API Service Interface
 * all the REST requests for the IMDB API are defined here
 */
interface MainApiService {
    /**
     * Get all the vehicles available within a given bounding box
     * defined by the top left and bottom right coordinates
     *
     * @param page current page
     * @param sortBy default popularity
     * @param includeAdult should adult content be included default false
     * @param includeVideo should video be included
     * @param releasedBeforeDate movie released before
     * @param year Release year
     *
     * @return list of all the movies
     */
    @GET("/discover/movie")
    suspend fun loadMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("release_date.lte") releasedBeforeDate: String? = null,
        @Query("year.lte") year: String? = null,
    ): Response<MoviesDTO>
}