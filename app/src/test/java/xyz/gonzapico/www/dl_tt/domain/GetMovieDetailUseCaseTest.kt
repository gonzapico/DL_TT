package xyz.gonzapico.www.dl_tt.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import xyz.gonzapico.www.dl_tt.data.repositories.MovieRepository

class GetMovieDetailUseCaseTest {

    private lateinit var usecase: GetMovieDetailUseCase

    @Mock
    private val mockRepository: MovieRepository = mock()

    private val imdbId = "tt8531916"

    @Before
    fun setUp() {
        usecase = GetMovieDetailUseCase(mockRepository)
    }

    @Test
    fun testMovieListUseCase() {
        val suspendFunction = mock<MovieRepository>()

        runBlocking {
            suspendFunction.getMovieList()
        }

        runBlocking {
            verify(suspendFunction).getMovieList()
        }
    }

    @Test
    fun testMovieDetailUseCaseForAnSpecificMovie() {
        val suspendFunction = mock<MovieRepository>()

        runBlocking {
            suspendFunction.movieDetail(imdbId)
        }

        runBlocking {
            verify(suspendFunction).movieDetail(imdbId)
        }
    }
}