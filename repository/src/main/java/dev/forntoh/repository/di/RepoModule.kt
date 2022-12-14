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

package dev.forntoh.repository.di

import dev.forntoh.repository.MoviesRepo
import dev.forntoh.repository.MoviesRepoImpl
import dev.forntoh.web_service.datasources.ImdbNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Module to provide the repositories implementation
 * required for injection
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    /**
     * Provides the implementation of the repository
     * @param imdbNetworkDataSource the implementation of the IMDB network data source
     * @return the implementation of the Movies repository
     */
    @Provides
    fun provideVehiclesRepo(
        imdbNetworkDataSource: ImdbNetworkDataSource,
    ): MoviesRepo = MoviesRepoImpl(imdbNetworkDataSource)

}