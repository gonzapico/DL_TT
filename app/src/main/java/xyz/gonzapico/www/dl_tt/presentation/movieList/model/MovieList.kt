package xyz.gonzapico.www.dl_tt.presentation.movieList.model

data class MovieList(val movies: List<Movie?>?)

data class Movie(
    val title: String,
    val poster: String,
    val director: String = "",
    val timeDuration: String = "",
    val ratingList: String = "",
    val imdbId : String
)