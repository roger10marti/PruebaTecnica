package dev.roger.pruebatecnica.data.network

import android.util.Log
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.Serie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class SeriesService @Inject constructor(private val api: SeriesApiClient) {
    val api_key = "c6aeee577586ba38e487b74dfede5deb"

    suspend fun getSeries(): List<Serie> {
        return withContext(Dispatchers.IO) {
            //val response = api.getAllSeries(api_key,"en-US","1")
            val response = api.getAllSeries()
            Log.d("ROGER", response.body()?.totalPages.toString())
            Log.d("ROGER", response.body()?.series!!.size.toString())
            response.body()!!.series ?: emptyList()
        }
    }
}