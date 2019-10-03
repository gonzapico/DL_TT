package xyz.gonzapico.www.dl_tt.domain.models

import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

data class MovieDataModel(val title: String,
                          val imdbID: String,
                          val poster: String,
                          val director: String = "",
                          val runtime: String = "",
                          val ratingList : String = "") : IData {
    fun toUI(): Movie? {
        return Movie(title, poster, director, runtime, "", imdbID)
    }
}

data class MovieListDataModel(val movies: List<MovieDataModel>) : IData {
    fun toUI(): MovieList? {
        val listOfMovies = movies.map {
            it.toUI()
        }
        return MovieList(listOfMovies)
    }
}