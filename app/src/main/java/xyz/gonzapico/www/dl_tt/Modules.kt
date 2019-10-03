package xyz.gonzapico.www.dl_tt

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import xyz.gonzapico.www.dl_tt.data.datasource.MovieRemoteDataSourceImpl
import xyz.gonzapico.www.dl_tt.data.network.OMDBAPI
import xyz.gonzapico.www.dl_tt.data.network.createNetworkClient
import xyz.gonzapico.www.dl_tt.data.repositories.MovieRepository
import xyz.gonzapico.www.dl_tt.domain.GetMovieDetailUseCase
import xyz.gonzapico.www.dl_tt.domain.GetMovieListUseCase
import xyz.gonzapico.www.dl_tt.presentation.movieDetail.MovieDetailPresenter
import xyz.gonzapico.www.dl_tt.presentation.movieList.MovieListPresenter


fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(repositoryModule)
}


val repositoryModule: Module = module {
    factory { GetMovieListUseCase(movieRepository = get()) }
    factory { GetMovieDetailUseCase(movieRepository = get()) }

    single { MovieListPresenter(getMovieListUseCase = get()) }
    single { MovieDetailPresenter(getMovieDetailUseCase = get()) }

    single { MovieRepository(remoteDataSource = get()) }

    single { MovieRemoteDataSourceImpl(api = moviesApi)}

    single { moviesApi }
}

private const val BASE_URL = "http://www.omdbapi.com/"
const val API_KEY = "ae39c34e"
const val TYPE_OF_FILMS = "love"
const val DOCLINE_LOGO_URL = "https://andalucia.openfuture.org/wp-content/uploads/2018/01/DL_logo_bicolor_positivo_rgb.png"

private val retrofit: Retrofit = createNetworkClient(BASE_URL)

private val moviesApi: OMDBAPI = retrofit.create(OMDBAPI::class.java)