package dev.roger.pruebatecnica.data.network

import dev.roger.pruebatecnica.data.Model.Generic
import retrofit2.Response
import retrofit2.http.GET

interface SeriesApiClient {
    @GET("1")
    suspend fun getAllSeries(): Response<List<Generic>>
}