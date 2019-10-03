package xyz.gonzapico.www.dl_tt.presentation.movieList

import kotlinx.coroutines.*
import xyz.gonzapico.www.dl_tt.domain.GetMovieListUseCase
import kotlin.coroutines.CoroutineContext

class MovieListPresenter constructor(private val getMovieListUseCase: GetMovieListUseCase) : CoroutineScope{

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun getMovieList(view: MovieListView) {
        view.showLoading()

        launch (Dispatchers.Main){
            val result = getMovieListUseCase.getMovieList()
                view.renderMovieList(result)
                view.hideLoading()
        }




            /*
            getMovieListUseCase.getMovieList(object : IServiceCallback<MovieListDataModel?> {
                override fun onError(error: Error) {
                    view.showError()
                }

                override fun onSuccess(data: MovieListDataModel?) {
                    val movieList =
                        data?.movies?.map {
                            Movie(title = it.title, poster = it.poster, imdbId = it.imdbID)
                        }
                    view.renderMovieList(MovieList(movieList))
                    view.hideLoading()
                }
            })
            */
        /*
        MovieService().getMovieList(object : IServiceCallback<MovieListDataModel?> {
            override fun onError(error: Error) {
                view.showError()
            }

            override fun onSuccess(data: MovieListDataModel?) {
                val movieList =
                    data?.movies?.map {
                        Movie(title = it.title, poster = it.poster, imdbId = it.imdbID)
                    }
                view.renderMovieList(MovieList(movieList))
                view.hideLoading()
            }
        })
         */
    }
}