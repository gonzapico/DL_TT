package xyz.gonzapico.www.dl_tt.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import xyz.gonzapico.www.dl_tt.injectFeature

open class BaseActivity : AppCompatActivity() {
    val navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        injectFeature()
    }
}