package dev.roger.pruebatecnica.domain

import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.data.Model.SerieProvider
import javax.inject.Inject

class GetAllSeriesUseCase @Inject constructor(private val serieProvider: SerieProvider) {

    operator fun invoke():List<Serie>?{
        val series = serieProvider.series
        if(!series.isNullOrEmpty()){
            return series
        }
        return null
    }

}