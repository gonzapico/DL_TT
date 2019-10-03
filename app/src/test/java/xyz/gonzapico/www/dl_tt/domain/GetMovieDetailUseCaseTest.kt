package xyz.gonzapico.www.dl_tt.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import xyz.gonzapico.www.dl_tt.data.repositories.MovieRepository
import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailPresenter
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailView

class GetMovieDetailUseCaseTest {

    private lateinit var usecase: GetMovieDetailUseCase
    private val testDispatcher = TestCoroutineDispatcher()


    private val mockRepository: MovieRepository = mock()

    private val imdbId = "tt8531916"
    private val movie = MovieDataModel("abc", imdbId, "")

    @Before
    fun setUp() {
        usecase = GetMovieDetailUseCase(mockRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getMovieDetailSuccess() = testDispatcher.runBlockingTest {
        // arrange
        val repository = Mockito.mock(MovieRepository::class.java)
        val view = Mockito.mock(MovieDetailView::class.java)
        val getMovieDetailUseCase = GetMovieDetailUseCase(repository)
        val presenter = MovieDetailPresenter(getMovieDetailUseCase)

        val expectedResult = MovieDataModel(
            "Dial H for Health Inspector",
            "https://m.media-amazon.com/images/M/MV5BODY2N2FiZGUtN2MzMC00M2U0LThlYjEtZjA5NjU5YTRiNjZhXkEyXkFqcGdeQXVyMDAwMzMyMw@@._V1_SX300.jpg",
            "Ronald Licona",
            "9 min",
            "",
            "tt8531916"
        )
        Mockito.`when`(repository.movieDetail("tt8531916")).thenReturn(expectedResult)

        // then
        verify(mockRepository).movieDetail(imdbId)

        getMovieDetailUseCase.getMovieDetail(imdbId)
        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(commentList)

        // given
        whenever(mockRepository.movieDetail(imdbId)).thenReturn(movie)

        // when
        val test = usecase.get(postId, false).test()

        // then
        verify(mockRepository).movieDetail(imdbId)
    }

    fun getMovieDetailTest() {
        MainScope().launch {
            mockRepository.movieDetail("tt8531916")
        }
    }

    @Test
    fun getMovieDetailFailure() {
        runBlockingTest {
            // arrange
            val repository = Mockito.mock(MovieRepository::class.java)

            val expectedResult = MovieDataModel(
                "Dial H for Health Inspector",
                "tt8531916",
                "https://m.media-amazon.com/images/M/MV5BODY2N2FiZGUtN2MzMC00M2U0LThlYjEtZjA5NjU5YTRiNjZhXkEyXkFqcGdeQXVyMDAwMzMyMw@@._V1_SX300.jpg",
                "Ronald Licona",
                "9 min",
                ""
            )
            Mockito.`when`(repository.movieDetail("tt8531916")).thenReturn(expectedResult)
        }
    }
}