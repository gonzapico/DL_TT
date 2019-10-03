package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import kotlinx.coroutines.*
import xyz.gonzapico.www.dl_tt.domain.GetMovieDetailUseCase
import kotlin.coroutines.CoroutineContext

class MovieDetailPresenter constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) :
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun getMovieDetail(view: MovieDetailView, idIMDB: String) {
        view.showLoading()
        launch(Dispatchers.Main) {
            val result = getMovieDetailUseCase.getMovieDetail(idIMDB)
            // withContext(Dispatchers.Main) {
                view.renderMovie(result)
                view.hideLoading()
            //}
        }

    }
}