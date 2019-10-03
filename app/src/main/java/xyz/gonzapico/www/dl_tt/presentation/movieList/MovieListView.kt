package xyz.gonzapico.www.dl_tt.presentation.movieList

import xyz.gonzapico.www.dl_tt.presentation.IPresenterView
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

interface MovieListView : IPresenterView {
    fun showLoading()

    fun hideLoading()

    fun renderMovieList(listOfMovies: MovieList?)

    fun showError()
}