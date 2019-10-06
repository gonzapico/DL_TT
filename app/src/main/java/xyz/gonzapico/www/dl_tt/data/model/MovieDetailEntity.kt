package xyz.gonzapico.www.dl_tt.data.model

import com.google.gson.annotations.SerializedName
import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.domain.models.RatingDomainModel

data class MovieDetailEntity(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
) {
    fun transformToDomain(): MovieDataModel? {
        return MovieDataModel(Title, Year, imdbID, Poster, Ratings.map{
            it.transformToDomain()
        })
    }
}

data class Rating(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String
) {
    fun transformToDomain() : RatingDomainModel{
        return RatingDomainModel(source, value)
    }
}