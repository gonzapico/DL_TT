package xyz.gonzapico.www.dl_tt.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.gonzapico.www.dl_tt.BuildConfig


fun createNetworkClient(urlBase : String): Retrofit {
    val logging = HttpLoggingInterceptor()
    val level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    logging.level = level

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    return Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}