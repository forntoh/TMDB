package dev.forntoh.common.entities

data class DiscoverMoviesFilter(
    var page: Int = 0,
    val sortBy: String = "popularity.desc",
    val includeAdult: Boolean = false,
    val includeVideo: Boolean = false,
    val releasedBeforeDate: String? = null,
    val year: Int? = null,
)
