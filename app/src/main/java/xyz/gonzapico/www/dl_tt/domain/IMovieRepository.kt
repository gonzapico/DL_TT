package xyz.gonzapico.www.dl_tt.domain

import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.domain.models.MovieListDataModel

interface IMovieRepository {
    suspend fun getMovieList(): MovieListDataModel?
    suspend fun movieDetail(idIDMB: String): MovieDataModel?
}