package xyz.gonzapico.www.dl_tt.domain.models

import xyz.gonzapico.www.dl_tt.presentation.movieDetail.ratingList.Rating
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

data class MovieDataModel(val title: String,
                          val year: String,
                          val imdbID: String,
                          val poster: String = "",
                          val ratingList: List<RatingDomainModel> = emptyList()){
    fun toUI(): Movie? {
        return Movie(title, year, imdbID, poster, ratingList.map { Rating(it.source, it.rate) })
    }
}

data class MovieListDataModel(val movies: List<MovieDataModel>) {
    fun toUI(): MovieList? {
        val listOfMovies = movies.map {
            it.toUI()
        }
        return MovieList(listOfMovies)
    }
}