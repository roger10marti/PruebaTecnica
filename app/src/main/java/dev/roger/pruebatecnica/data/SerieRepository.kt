package dev.roger.pruebatecnica.data

import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.SerieProvider
import dev.roger.pruebatecnica.data.network.SeriesService
import javax.inject.Inject

class SerieRepository @Inject constructor(
    private val api: SeriesService,
    private val serieProvider: SerieProvider
) {

    suspend fun getAllSeries(): List<Generic> {
        val response =api.getSeries()
        serieProvider.series = response
        return response
    }
}