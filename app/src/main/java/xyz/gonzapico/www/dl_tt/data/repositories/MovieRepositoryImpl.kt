package xyz.gonzapico.www.dl_tt.data.repositories

import xyz.gonzapico.www.dl_tt.API_KEY
import xyz.gonzapico.www.dl_tt.TYPE_OF_FILMS
import xyz.gonzapico.www.dl_tt.data.datasource.MovieDataSource
import xyz.gonzapico.www.dl_tt.data.datasource.MovieRemoteDataSourceImpl
import xyz.gonzapico.www.dl_tt.domain.IMovieRepository
import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.domain.models.MovieListDataModel

class MovieRepository constructor(private val remoteDataSource: MovieRemoteDataSourceImpl) :
    IMovieRepository {
    override suspend fun movieDetail(idIDMB: String): MovieDataModel? {
        val result = remoteDataSource.getMovie(idIDMB, API_KEY).await()
        return result.transformToDomain()
    }

    override suspend fun getMovieList(): MovieListDataModel? {
        val result = remoteDataSource.searchFilm(TYPE_OF_FILMS, API_KEY).await()
        return result.transformToDomain()
    }
}