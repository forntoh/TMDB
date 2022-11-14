package dev.forntoh.web_service.dto

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    val page: Int,
    var results: List<MovieDTO> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int
)
