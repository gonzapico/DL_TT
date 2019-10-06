package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie

interface MovieDetailView {
    fun showLoading()

    fun hideLoading()

    fun renderMovie(movieDetail: Movie?)

    fun showError()
}