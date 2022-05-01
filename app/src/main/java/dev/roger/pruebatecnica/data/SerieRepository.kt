package dev.roger.pruebatecnica.data

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.Provides
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.data.Model.SerieProvider
import dev.roger.pruebatecnica.data.network.SeriesService
import dev.roger.pruebatecnica.ui.ViewModel.SeriesViewModel
import javax.inject.Inject

class SerieRepository @Inject constructor(
    private val api: SeriesService,
    private val serieProvider: SerieProvider
) {

    suspend fun getSeriesPackage(language: String, i: Int): List<Serie> {
        val response = api.getSeries(language, i)
        Log.d("ROGER getSeriesPackage", response.size.toString())
        serieProvider.series = response
        return response
    }

    suspend fun getTotalPages(): Int {
        val response = api.getTotalPages()
        return response
    }

    suspend fun getSeriesWithoutConnection(language: String, i: Int): List<Serie> {
        val response = api.getSeriesNoWifi(language, i)
        serieProvider.series = response
        return response
    }

    suspend fun getSeriesWithoutConnectionQty(): Int {
        val response = api.getSeriesNoWifiQty()
        return response
    }
}