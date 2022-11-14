package dev.forntoh.common.entities

import androidx.annotation.Keep

@Keep
data class Movie(
    val id: Int,
    val title: String,
    val poster: String? = null,
    val backdrop: String? = null,
    val genres: List<Genre>? = null,
    val language: String,
    val longDescription: String,
    val shortDescription: String? = null,
    val status: String? = null,
    val rating: Double = 0.0,
    val runtime: Int = 0,
    val releaseDate: String,
)
