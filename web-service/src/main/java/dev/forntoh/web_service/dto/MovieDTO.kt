package dev.forntoh.web_service.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import dev.forntoh.common.entities.Genre
import dev.forntoh.common.entities.Movie
import dev.forntoh.web_service.base.BaseUrl

@Keep
data class MovieDTO(
    val id: Int,

    @SerializedName("backdrop_path") val backdropPath: String?,

    val genres: List<Genre> = emptyList(),

    @SerializedName("original_language") val originalLanguage: String,

    val title: String,

    @SerializedName("poster_path") val posterPath: String?,

    val overview: String,

    val tagline: String?,

    @SerializedName("vote_average") val voteAverage: Double,

    val status: String?,

    val runtime: Int,

    @SerializedName("release_date") val releaseDate: String,
)

/**
 * Convert [MovieDTO] to [Movie] entity
 */
fun MovieDTO.toModel(): Movie = Movie(
    id = id,
    title = title,
    poster = "${BaseUrl.imageBaseUrl}$posterPath",
    backdrop = "${BaseUrl.imageBaseUrl}$backdropPath",
    genres = genres,
    language = originalLanguage,
    longDescription = overview,
    shortDescription = tagline,
    status = status,
    rating = voteAverage,
    runtime = runtime,
    releaseDate = releaseDate
)