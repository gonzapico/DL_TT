package xyz.gonzapico.www.dl_tt.presentation

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String) {
    Picasso.get().load(url).fit().into(this)
}

fun ProgressBar.toggle() {
    if (this.visibility == View.VISIBLE)
        this.visibility = View.GONE
    else
        this.visibility = View.VISIBLE
}