package com.example.jesusapp.data.remote

import com.example.jesusapp.data.model.DairyCategoriesModel
import com.example.jesusapp.data.model.DataModel
import com.example.jesusapp.data.model.HomeDataModel1
import com.example.jesusapp.data.model.PrayerModel
import com.example.jesusapp.data.remote.response.MovieData
import com.example.jesusapp.data.remote.response.MovieListResponse
import com.example.jesusapp.ui.latestnews.NewsResponseModel
import com.example.jesusapp.ui.programs.ProgramResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Apis {

    @GET("/3/movie/popular?api_key=a0525b2df20e02083f3fa151f5819558")
    suspend fun getMoviesList( @Query("page") page:Int) : Response<MovieListResponse>

    @GET("/3/movie/{movie_id}?api_key=a0525b2df20e02083f3fa151f5819558")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: Int): Response<MovieData>

    @GET("/api/users?page=2")
    suspend fun getData(): DataModel

    @GET("/api/users?page=1")
    suspend fun getDatas(): Response<DataModel>

    /* @GET("/v2/top-headlines")
     suspend fun getNews(
         @Query("country")
         countryCode: String = "in",
         @Query("page")
         pageNumber: Int = 1,
         @Query("apiKey")
         apiKey: String = "7398c82f79f64bb8b68fd55fcca2c9ae"
     ): Response<NewsResponseModel>*/


    @POST("flash_news")
    suspend fun getNews(
        @Query("page_no")
        pageNumber: Int = 1,
        @Query("offset_limit")
        offset_limit: Int = 10
    ): Response<NewsResponseModel>

    @POST("programs")
    suspend fun getPrograms(
        @Query("page_no")
        pageNumber: Int = 1,
        @Query("offset_limit")
        offset_limit: Int = 10
    ): Response<ProgramResponseModel>

    @POST("/featureslist")
    suspend fun getHomePageData(): Response<HomeDataModel1>

    @POST("dairycategories")
    suspend fun getCategoryDiaryData(
        @Query("date")
        date: String
    ): Response<DairyCategoriesModel>

    @POST("prayercategories")
    suspend fun getPrayersData(): Response<PrayerModel>


}