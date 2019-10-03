package xyz.gonzapico.www.dl_tt.domain

import xyz.gonzapico.www.dl_tt.data.repositories.MovieRepository
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie

class GetMovieDetailUseCase constructor(private val movieRepository: MovieRepository) {
    suspend fun getMovieDetail(idIMDB: String): Movie? {
        return movieRepository.movieDetail(idIMDB)?.toUI()
    }
}