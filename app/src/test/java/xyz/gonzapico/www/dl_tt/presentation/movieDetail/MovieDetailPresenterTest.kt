package xyz.gonzapico.www.dl_tt.presentation.movieDetail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import xyz.gonzapico.www.dl_tt.data.repositories.MovieRepository
import xyz.gonzapico.www.dl_tt.domain.GetMovieDetailUseCase
import xyz.gonzapico.www.dl_tt.presentation.movieList.model.Movie

class MovieDetailPresenterTest {

    private lateinit var presenter: MovieDetailPresenter

    @Mock
    private lateinit var view: MovieDetailView

    @Mock
    private lateinit var mockMovieDetailUseCase: GetMovieDetailUseCase

    val idIMDB = "tt8531916"
    val expectedInfo = Movie("", "", idIMDB, "")
    private val testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        presenter = MovieDetailPresenter(mockMovieDetailUseCase)
        // provide the scope explicitly, in this example using a constructor parameter
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun renderMovie() = testDispatcher.runBlockingTest {
        Mockito.`when`(mockMovieDetailUseCase.getMovieDetail(idIMDB = idIMDB))
            .thenReturn(expectedInfo)
        getMovieDetailTest()
        verify(view).renderMovie(expectedInfo)
        verify(view).hideLoading()
    }

    fun getMovieDetailTest() {
        MainScope().launch {
            presenter.getMovieDetail(view, idIMDB)  // executes after advanceTimeBy(1_000)
        }
    }
}