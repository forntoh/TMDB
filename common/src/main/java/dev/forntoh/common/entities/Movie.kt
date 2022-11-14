package dev.forntoh.common.entities

import androidx.annotation.Keep

@Keep
data class Movie(
    val id: Int,
    val title: String,
    val poster: String?,
    val backdrop: String?,
    val genres: List<Genre>?,
    val language: String,
    val longDescription: String,
    val shortDescription: String?,
    val status: String?,
    val rating: Double,
    val runtime: Int = 0,
    val releaseDate: String,
)
