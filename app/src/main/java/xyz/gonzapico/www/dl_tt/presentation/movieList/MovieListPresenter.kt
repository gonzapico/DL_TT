package xyz.gonzapico.www.dl_tt.presentation.movieList

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import xyz.gonzapico.www.dl_tt.domain.GetMovieListUseCase
import kotlin.coroutines.CoroutineContext

class MovieListPresenter constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    fun getMovieList(view: MovieListView) {
        view.showLoading()
        launch(Dispatchers.Default) {
            val result = getMovieListUseCase.getMovieList()
            launch(Dispatchers.Main){
                view.renderMovieList(result)
                view.hideLoading()
            }
        }

    }
}