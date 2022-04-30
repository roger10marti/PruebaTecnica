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

    suspend fun getSeriesPackage(): List<Serie> {
        val response = api.getSeries()
        Log.d("ROGER getSeriesPackage", response.size.toString())
        serieProvider.series = response
        return response
    }
}