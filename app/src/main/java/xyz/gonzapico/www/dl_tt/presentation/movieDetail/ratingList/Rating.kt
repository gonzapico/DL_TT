package xyz.gonzapico.www.dl_tt.presentation.movieDetail.ratingList

import android.os.Parcel
import android.os.Parcelable

data class Rating(val source: String,
                  val value: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    fun transformToPercentage() : Int {
        when {
            value.contains("%") -> return value.replace("%","").toInt()
            value.endsWith("/100") -> return value.substring(0, value.indexOf("/")).replace(".","").toInt()
            value.endsWith("/10") -> return value.substring(0, value.indexOf("/")).replace(".","").toInt()
            else -> return 0
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(source)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rating> {
        override fun createFromParcel(parcel: Parcel): Rating {
            return Rating(parcel)
        }

        override fun newArray(size: Int): Array<Rating?> {
            return arrayOfNulls(size)
        }
    }
}