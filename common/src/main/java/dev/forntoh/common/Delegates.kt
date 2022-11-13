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

package dev.forntoh.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This function is used to perform operations on the main thread.
 */
suspend fun <T> runOnUiThread(block: () -> T) {
    withContext(Dispatchers.Main) {
        block.invoke()
    }
}

/**
 * This function is used to perform operations on the IO thread.
 */
suspend fun <T> runOnIoThread(block: suspend () -> T) = withContext(Dispatchers.IO) {
    return@withContext block.invoke()
}