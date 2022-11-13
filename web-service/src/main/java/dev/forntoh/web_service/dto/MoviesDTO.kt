package dev.forntoh.web_service.dto

data class MoviesDTO(
    val page: Int,
    val results: List<MovieDTO> = emptyList(),
    val totalPages: Int
)
