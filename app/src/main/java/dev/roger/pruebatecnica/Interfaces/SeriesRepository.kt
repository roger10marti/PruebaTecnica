package dev.roger.pruebatecnica.Interfaces

import dev.roger.pruebatecnica.data.Model.Generic
import retrofit2.http.GET

interface SeriesRepository {
    @GET("1")
    fun getSeriesPackage(pageNumber: Int): List<Generic>
}