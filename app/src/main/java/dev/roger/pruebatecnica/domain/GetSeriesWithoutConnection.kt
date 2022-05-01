package dev.roger.pruebatecnica.domain

import dev.roger.pruebatecnica.data.SerieRepository
import javax.inject.Inject

class GetSeriesWithoutConnection @Inject constructor(private val seriesRepository: SerieRepository){
    suspend operator fun invoke(language: String,i: Int) = seriesRepository.getSeriesWithoutConnection(language,i)
}