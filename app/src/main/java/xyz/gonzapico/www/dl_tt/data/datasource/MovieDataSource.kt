package xyz.gonzapico.www.dl_tt.data.datasource

import kotlinx.coroutines.Deferred
import xyz.gonzapico.www.dl_tt.data.model.MovieDetailEntity
import xyz.gonzapico.www.dl_tt.data.model.SearchEntity

interface MovieDataSource {

    fun searchFilm(
        filmToSearch: String,
        apiKey: String
    ): Deferred<SearchEntity>

    fun getMovie(
        idIMDB: String,
        apiKey: String
    ): Deferred<MovieDetailEntity>
}