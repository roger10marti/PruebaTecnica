package dev.roger.pruebatecnica.data.network

import dev.roger.pruebatecnica.data.Model.Generic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesService @Inject constructor(private val api: SeriesApiClient) {

    suspend fun getSeries(): List<Generic> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllSeries()
            response.body() ?: emptyList()
        }
    }
}