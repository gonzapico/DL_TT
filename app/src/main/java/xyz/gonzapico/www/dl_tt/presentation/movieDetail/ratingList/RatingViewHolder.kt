package xyz.gonzapico.www.dl_tt.presentation.movieList

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list_rating.view.*

class RatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvRatingValue: TextView = view.tv_rating_value
    val pbRatingValue: ProgressBar = view.pb_rating_value
    val tvSource : TextView = view.rating_source
}