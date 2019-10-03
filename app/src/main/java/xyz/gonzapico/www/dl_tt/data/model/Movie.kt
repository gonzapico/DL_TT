package xyz.gonzapico.www.dl_tt.data.model

import com.google.gson.annotations.SerializedName
import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel

data class Movie(
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Year")
    val year: String,
    val imdbID: String
) {
    fun transformToDomain(): MovieDataModel {
        return MovieDataModel(title, imdbID, poster)

    }
}