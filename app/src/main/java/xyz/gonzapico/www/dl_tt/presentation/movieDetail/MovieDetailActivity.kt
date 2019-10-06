package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_item_detail.*
import xyz.gonzapico.www.dl_tt.R
import xyz.gonzapico.www.dl_tt.presentation.BaseActivity
import xyz.gonzapico.www.dl_tt.presentation.movieList.MovieListActivity

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [MovieListActivity].
 */
class MovieDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }

        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)


        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        MovieDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(MovieDetailFragment.ARG_ITEM_ID)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment, "prueba")
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.item_detail_container, supportFragmentManager.findFragmentByTag("prueba")!!)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, MovieListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
