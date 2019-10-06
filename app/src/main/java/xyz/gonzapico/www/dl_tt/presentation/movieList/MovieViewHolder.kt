package xyz.gonzapico.www.dl_tt.presentation.movieList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_content.view.*

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.title
    val year: TextView = view.year
}