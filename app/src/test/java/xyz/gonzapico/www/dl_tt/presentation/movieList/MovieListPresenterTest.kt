package xyz.gonzapico.www.dl_tt.presentation.movieList

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import xyz.gonzapico.www.dl_tt.domain.GetMovieDetailUseCase
import xyz.gonzapico.www.dl_tt.domain.GetMovieListUseCase
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailPresenter
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailView
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.MovieList

class MovieListPresenterTest {
    private lateinit var presenter: MovieListPresenter

    @Mock
    private lateinit var view: MovieListView

    @Mock
    private lateinit var mockMovieListUseCase: GetMovieListUseCase

    val expectedInfo = MovieList(listOf())
    private val testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        presenter = MovieListPresenter(mockMovieListUseCase)
        // provide the scope explicitly, in this example using a constructor parameter
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun renderMovieList() = testDispatcher.runBlockingTest {
        Mockito.`when`(mockMovieListUseCase.getMovieList())
            .thenReturn(expectedInfo)
        getMovieListTest()
        Mockito.verify(view).renderMovieList(expectedInfo)
        Mockito.verify(view).hideLoading()
    }

    fun getMovieListTest() {
        MainScope().launch {
            presenter.getMovieList(view)
        }
    }
}