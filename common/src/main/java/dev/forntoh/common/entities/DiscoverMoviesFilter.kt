package dev.forntoh.common.entities

data class DiscoverMoviesFilter(
    val page: Int = 1,
    val sortBy: String = "popularity.desc",
    val includeAdult: Boolean = false,
    val includeVideo: Boolean = false,
    val releasedBeforeDate: String? = null,
    val year: String? = null,
)
