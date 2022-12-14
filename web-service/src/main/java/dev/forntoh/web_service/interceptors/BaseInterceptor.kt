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

package dev.forntoh.web_service.interceptors

import dev.forntoh.web_service.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

/**
 * Interceptor to add headers to the request
 */
class BaseInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl
            .newBuilder()
            .addQueryParameter("language", Locale.getDefault().toLanguageTag())
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val request = originalRequest
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Connection", "Keep-Alive")
            .addHeader("Content-Type", "application/json")
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }

}