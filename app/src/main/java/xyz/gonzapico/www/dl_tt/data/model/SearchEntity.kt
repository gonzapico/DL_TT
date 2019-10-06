package xyz.gonzapico.www.dl_tt.data.model

import com.google.gson.annotations.SerializedName
import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.domain.models.MovieListDataModel

data class SearchEntity(
    @SerializedName("Response")
    val Response: String,
    @SerializedName("Search")
    val search: List<MovieEntity>,
    val totalResults: String
) {
    fun transformToDomain(): MovieListDataModel? {
        val movies: List<MovieDataModel> = search.map {
            it.transformToDomain()
        }
        return MovieListDataModel(movies)

    }
}