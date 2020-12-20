package com.example.jesusapp.di.module

import androidx.viewbinding.BuildConfig
import com.example.jesusapp.data.remote.Apis
import com.example.jesusapp.data.remote.datasource.MovieListDataSource
import com.example.jesusapp.data.repo.MovieListRepo
import com.example.jesusapp.utils.ConstantValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl() = ConstantValues.BASE_URL2

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient {
        val client = if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                 .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .build()
        } else OkHttpClient
            .Builder()
            .build()

        /*client.interceptors(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response? {
                var request: Request = chain.request()
                val url: HttpUrl =
                    request.url().newBuilder()
                        .addQueryParameter("api_key", ConstantValues.API_KEY)
                        .addQueryParameter("language",ConstantValues.BASE_LANG)
                        .build()
                request = request.newBuilder().url(url).build()
                return chain.proceed(request)
            }
        })*/
        return client;
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Apis =
        retrofit.create(Apis::class.java)

    @Provides
    @Singleton
    fun provideMoviesListDataSource(api: Apis) = MovieListDataSource(api)

    @Provides
    @Singleton
    fun provideMovieListRepo(ds: MovieListDataSource) = MovieListRepo(ds)


}