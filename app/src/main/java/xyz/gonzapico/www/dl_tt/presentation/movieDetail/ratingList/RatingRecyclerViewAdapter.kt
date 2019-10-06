package xyz.gonzapico.www.dl_tt.presentation.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.ratingList.Rating
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

class RatingRecyclerViewAdapter(
    private val ratingList: List<Rating>
) : RecyclerView.Adapter<RatingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_rating, parent, false))
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val item = ratingList[position]
        holder.pbRatingValue.progress = item.transformToPercentage()
        holder.tvRatingValue.text = item.value
        holder.tvSource.text = item.source
    }

    override fun getItemCount() = ratingList.size
}