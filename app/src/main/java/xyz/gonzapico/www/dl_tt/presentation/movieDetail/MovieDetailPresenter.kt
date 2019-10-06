package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import xyz.gonzapico.www.dl_tt.domain.GetMovieDetailUseCase
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import kotlin.coroutines.CoroutineContext

class MovieDetailPresenter constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) :
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    fun getMovieDetail(view: MovieDetailView, idIMDB: String) {
        view.showLoading()
        var result : Movie?
        launch(Dispatchers.Default) {
            result = getMovieDetailUseCase.getMovieDetail(idIMDB)
            launch(Dispatchers.Main) {
                view.renderMovie(result)
                view.hideLoading()
            }
        }
    }
}