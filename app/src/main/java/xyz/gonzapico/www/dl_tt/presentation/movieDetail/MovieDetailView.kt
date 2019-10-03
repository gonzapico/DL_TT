package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import xyz.gonzapico.www.dl_tt.presentation.IPresenterView
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

interface MovieDetailView : IPresenterView {
    fun showLoading()

    fun hideLoading()

    fun renderMovie(movieDetail: Movie?)

    fun showError()
}