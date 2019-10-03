package xyz.gonzapico.www.dl_tt.data.datasource

import kotlinx.coroutines.Deferred
import xyz.gonzapico.www.dl_tt.data.model.MovieDetail
import xyz.gonzapico.www.dl_tt.data.model.Search
import xyz.gonzapico.www.dl_tt.data.network.OMDBAPI

class MovieRemoteDataSourceImpl constructor(
    private val api: OMDBAPI
) : MovieDataSource {

    override fun getMovie(idIMDB: String, apiKey: String): Deferred<MovieDetail> {
        return api.getMovie(idIMDB, apiKey)
    }

    override fun searchFilm(filmToSearch: String, apiKey: String): Deferred<Search> {
        return api.searchFilm(filmToSearch, apiKey)
    }
}