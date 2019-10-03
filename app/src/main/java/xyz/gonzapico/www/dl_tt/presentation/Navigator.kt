package xyz.gonzapico.www.dl_tt.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailActivity
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailFragment
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie

class Navigator {

    fun navigateToDetailMovie(
        twoPane: Boolean,
        parentActivity: AppCompatActivity,
        item: Movie,
        v: View
    ) {
        if (twoPane) {
            navigateToDetailMovie(item, parentActivity)
        } else {
            navigateToDetailMovie(v, item)
        }
    }

    private fun navigateToDetailMovie(item: Movie, parentActivity: AppCompatActivity) {
        val fragment = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putString(MovieDetailFragment.ARG_ITEM_ID, item.imdbId)
            }
        }
        parentActivity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.item_detail_container, fragment)
            .commit()
    }

    private fun navigateToDetailMovie(
        v: View,
        item: Movie
    ) {
        val intent = Intent(v.context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailFragment.ARG_ITEM_ID, item.imdbId)
        }
        v.context.startActivity(intent)
    }

}