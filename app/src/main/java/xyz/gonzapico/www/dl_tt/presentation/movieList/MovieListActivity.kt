package xyz.gonzapico.www.dl_tt.presentation.movieList

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.injectFeature
import xyz.gonzapico.www.dl_tt.presentation.BaseActivity
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList
import xyz.gonzapico.www.dl_tt.presentation.toggle

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [MovieDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MovieListActivity : BaseActivity(), MovieListView {
    private val movieListPresenter: MovieListPresenter by inject()

    override fun showLoading() {
        pbLoading.toggle()
    }

    override fun hideLoading() {
        pbLoading.toggle()
    }

    override fun renderMovieList(listOfMovies: MovieList?) {
        item_list.adapter =
            MovieRecyclerViewAdapter(
                this,
                listOfMovies,
                twoPane
            )
    }

    override fun showError() {
        Snackbar.make(
            frameLayout,
            resources.getString(R.string.error_message),
            Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }

        setContentView(R.layout.activity_item_list)

        injectFeature()

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView()
        movieListPresenter.getMovieList(this)

    }

    private fun setupRecyclerView() {
        item_list.adapter =
            MovieRecyclerViewAdapter(
                this,
                MovieList(emptyList()),
                twoPane
            )
    }
}
