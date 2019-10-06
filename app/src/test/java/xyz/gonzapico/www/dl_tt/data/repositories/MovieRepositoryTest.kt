package xyz.gonzapico.www.dl_tt.data.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import xyz.gonzapico.www.dl_tt.API_KEY
import xyz.gonzapico.www.dl_tt.data.datasource.MovieRemoteDataSourceImpl
import xyz.gonzapico.www.dl_tt.data.model.MovieDetailEntity
import xyz.gonzapico.www.dl_tt.data.model.SearchEntity
import xyz.gonzapico.www.dl_tt.domain.IMovieRepository
import xyz.gonzapico.www.dl_tt.domain.models.MovieDataModel
import xyz.gonzapico.www.dl_tt.domain.models.MovieListDataModel

class MovieRepositoryTest {

    // SUT
    lateinit var movieRepository: IMovieRepository

    // Collaborators
    lateinit var movieRemoteDS: MovieRemoteDataSourceImpl

    // Models
    lateinit var movieDetailEntity: MovieDetailEntity
    lateinit var movieEntityList : List<xyz.gonzapico.www.dl_tt.data.model.MovieEntity>
    var movieDataModelDetail: MovieDataModel? = null
    var movieListDataModelDetail: MovieListDataModel? = null
    lateinit var searchEntity : SearchEntity

    @Before
    fun before() {
        setUpSpecificMovie()
        setUpListOfMovies()

        movieRemoteDS = mock()

        whenever(movieRemoteDS.getMovie("tt8531916", API_KEY)).thenReturn(movieDetailEntity.toDeferred())
        whenever(movieRemoteDS.searchFilm("war", API_KEY)).thenReturn(searchEntity.toDeferred())

        movieRepository = MovieRepository(movieRemoteDS)
    }

    private fun setUpSpecificMovie(){
        movieDetailEntity = mock()
        movieDataModelDetail = mock()
        movieDetailEntity = MovieDetailEntity(
            "",
            "", "", "", "", "", "", "",
            "", "", "", "", "", emptyList(), "", "", "", "Hola",
            "", "", "","", "", "", "")
        movieDataModelDetail = MovieDataModel("title", "", "tt8531916", "poster")
    }

    private fun setUpListOfMovies(){
        movieListDataModelDetail = mock()
        movieEntityList = listOf(xyz.gonzapico.www.dl_tt.data.model.MovieEntity("Adios", "", "war", "2020", "tt8531916"))
        searchEntity = SearchEntity("OK", movieEntityList, "1")
    }

    @Test
    fun repositoryAsksForAnSpecificMovie() {
        runBlocking {
            movieDataModelDetail = movieRepository.movieDetail("tt8531916")
        }
        assertEquals(movieDataModelDetail?.title, "Hola")
    }

    @Test
    fun repositoryAsksForAListOfMovies() {
        runBlocking {
            movieListDataModelDetail = movieRepository.getMovieList()
        }

        assertEquals(movieListDataModelDetail?.movies?.size, 1)
    }

    fun <T> T.toDeferred() = CompletableDeferred(this)
}