package xyz.gonzapico.www.dl_tt.domain

import xyz.gonzapico.www.dl_tt.data.repositories.MovieRepository
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

class GetMovieListUseCase constructor(private val movieRepository: MovieRepository) {
    suspend fun getMovieList() : MovieList? {
        return movieRepository.getMovieList()?.toUI()
    }
}