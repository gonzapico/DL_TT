package xyz.gonzapico.www.dl_tt.data.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import xyz.gonzapico.www.dl_tt.API_KEY
import xyz.gonzapico.www.dl_tt.data.datasource.MovieRemoteDataSourceImpl

class MovieRepositoryTest {
    private lateinit var repository: MovieRepository

    private val mockRemoteDataSource: MovieRemoteDataSourceImpl = mock()

    private val imdbID = "tt8531916"

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = MovieRepository(mockRemoteDataSource)
    }

    @Test
    fun `get movies remote success`(){
        // given
        // whenever(mockRemoteDataSource.getMovie(imdbID, API_KEY)).thenReturn(Single.just(remoteList))

        // when
        runBlockingTest {
            val test = repository.movieDetail(imdbID)
            val test2 = verify(mockRemoteDataSource).getMovie(imdbID, API_KEY)
            assertEquals(test, test2)
        }



    }
}