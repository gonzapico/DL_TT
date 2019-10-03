package xyz.gonzapico.www.dl_tt.data.model

import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.domain.models.MovieListDataModel

data class Search(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
) {
    fun transformToDomain(): MovieListDataModel? {
        val movies: List<MovieDataModel> = Search.map {
            it.transformToDomain()
        }
        return MovieListDataModel(movies)

    }
}