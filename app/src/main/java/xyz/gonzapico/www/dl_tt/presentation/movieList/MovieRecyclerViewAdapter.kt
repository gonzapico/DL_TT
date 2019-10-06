package xyz.gonzapico.www.dl_tt.presentation.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

class MovieRecyclerViewAdapter(
    private val parentActivity: MovieListActivity,
    private val movieList: MovieList?,
    private val twoPane: Boolean
) : RecyclerView.Adapter<MovieViewHolder>() {
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val movie = v.tag as Movie
            parentActivity.navigator.navigateToDetailMovie(twoPane, parentActivity, movie, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movieList?.movies?.get(position)
        holder.title.text = item?.title
        holder.year.text = item?.year

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = movieList?.movies?.size ?: 0
}