package xyz.gonzapico.www.dl_tt.data.datasource

import kotlinx.coroutines.Deferred
import xyz.gonzapico.www.dl_tt.data.model.MovieDetail
import xyz.gonzapico.www.dl_tt.data.model.Search

interface MovieDataSource {

    fun searchFilm(
        filmToSearch: String,
        apiKey: String
    ): Deferred<Search>

    fun getMovie(
        idIMDB: String,
        apiKey: String
    ): Deferred<MovieDetail>
}