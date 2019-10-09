package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject
import xyz.gonzapico.www.dl_tt.DOCLINE_LOGO_URL
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.presentation.BaseActivity
import xyz.gonzapico.www.dl_tt.presentation.loadUrl
import xyz.gonzapico.www.dl_tt.presentation.movieList.MovieRecyclerViewAdapter
import xyz.gonzapico.www.dl_tt.presentation.movieList.RatingRecyclerViewAdapter
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.toggle

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MovieDetailFragment : Fragment(), MovieDetailView {

    val KEY_MOVIE_LOADED = "KEY_MOVIE_LOADED"
    val KEY_FILM = "film"

    private val movieDetailPresenter : MovieDetailPresenter by inject()

    override fun showLoading() {
        activity?.pbLoading?.toggle()
    }

    override fun hideLoading() {
        activity?.pbLoading?.toggle()
    }

    override fun renderMovie(movieDetail: Movie?) {
        // Show the dummy content as text in a TextView.
        movieDetail.let {
            activity?.runOnUiThread {
                setUpUI(it)
            }
        }

        this.arguments?.putParcelable(KEY_FILM, movieDetail)
    }

    private fun setUpUI(it: Movie?) {
        activity?.toolbar_layout?.title = it?.title
        rootView.poster?.loadUrl(
            it?.poster
                ?: DOCLINE_LOGO_URL
        )
        activity?.header?.loadUrl(
            it?.poster
                ?: DOCLINE_LOGO_URL
        )
        rootView.title?.text = it?.title
        rootView.director?.text = it?.year
        rootView.rating_list?.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        rootView.rating_list?.adapter =
            it?.ratingList?.let { it1 ->
                RatingRecyclerViewAdapter(
                    it1
                )
            }
    }

    override fun showError() {
        //
    }

    /**
     * The dummy content this fragment is presenting.
     */
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // retain this fragment
        retainInstance = true

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                movieDetailPresenter.getMovieDetail(this, it.getString(ARG_ITEM_ID) ?: savedInstanceState?.getString(KEY_MOVIE_LOADED) ?: "")

                savedInstanceState?.putString(KEY_MOVIE_LOADED, it.getString(ARG_ITEM_ID)!!)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.item_detail, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()

        if (this.arguments?.getParcelable<Movie?>(KEY_FILM) != null)
            this.renderMovie(this.arguments?.getParcelable<Movie?>("film"))


    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        const val FRAGMENT_TAG = "films_tag"
    }
}
