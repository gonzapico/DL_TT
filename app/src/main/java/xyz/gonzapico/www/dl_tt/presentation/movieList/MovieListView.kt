package xyz.gonzapico.www.dl_tt.presentation.movieList

import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

interface MovieListView {
    fun showLoading()

    fun hideLoading()

    fun renderMovieList(listOfMovies: MovieList?)

    fun showError()
}