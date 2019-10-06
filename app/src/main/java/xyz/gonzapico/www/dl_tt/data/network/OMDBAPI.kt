package xyz.gonzapico.www.dl_tt.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.gonzapico.www.dl_tt.data.model.MovieDetailEntity
import xyz.gonzapico.www.dl_tt.data.model.SearchEntity

interface OMDBAPI {
    @GET("/")
    fun searchFilm(
        @Query("s") filmToSearch: String,
        @Query("apiKey") apiKey: String
    ): Deferred<SearchEntity>

    @GET("/")
    fun getMovie(
        @Query("i") idIMDB: String,
        @Query("apiKey") apiKey: String
    ): Deferred<MovieDetailEntity>
}