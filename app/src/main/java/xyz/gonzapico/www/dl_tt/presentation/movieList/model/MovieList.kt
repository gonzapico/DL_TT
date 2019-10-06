package xyz.gonzapico.www.dl_tt.presentation.movieList.model

import android.os.Parcel
import android.os.Parcelable
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.ratingList.Rating

data class MovieList(val movies: List<Movie?>?)

data class Movie(
    val title: String,
    val year: String,
    val imdbId : String,
    val poster: String,
    val ratingList: List<Rating> = emptyList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("ratingList")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(imdbId)
        parcel.writeString(poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}