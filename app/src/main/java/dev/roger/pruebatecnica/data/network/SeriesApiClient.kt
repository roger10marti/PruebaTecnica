package dev.roger.pruebatecnica.data.network

import dev.roger.pruebatecnica.data.Model.Generic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//val BASE_URL2 = "https://api.themoviedb.org/3/tv/popular?api_key="+ NetworkModule.api_key +"&language=en-US&page=1"
interface SeriesApiClient {
    /*@GET("tv/popular?")
    suspend fun getAllSeries(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") pageNumber: String): Response<Generic>*/

    @GET("tv/popular?api_key=c6aeee577586ba38e487b74dfede5deb&language=en-US&page=1")
    suspend fun getAllSeries(): Response<Generic>
}