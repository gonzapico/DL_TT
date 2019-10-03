package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import org.koin.android.ext.android.inject
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.presentation.BaseActivity
import xyz.gonzapico.www.dl_tt.presentation.loadUrl
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.toggle

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MovieDetailFragment : Fragment(), MovieDetailView {

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
    }

    private fun setUpUI(it: Movie?) {
        activity?.toolbar_layout?.title = it?.title
        rootView.poster?.loadUrl(
            it?.poster
                ?: "https://andalucia.openfuture.org/wp-content/uploads/2018/01/DL_logo_bicolor_positivo_rgb.png"
        )
        activity?.header?.loadUrl(
            it?.poster
                ?: "https://andalucia.openfuture.org/wp-content/uploads/2018/01/DL_logo_bicolor_positivo_rgb.png"
        )
        rootView.title?.text = it?.title
        rootView.director?.text = it?.director
        rootView.runtime?.text = it?.timeDuration
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

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                movieDetailPresenter.getMovieDetail(this, it.getString(ARG_ITEM_ID)!!)

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

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
