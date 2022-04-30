package dev.roger.pruebatecnica.domain

import dev.roger.pruebatecnica.data.SerieRepository
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val seriesRepository : SerieRepository) {

    suspend operator fun invoke() = seriesRepository.getSeriesPackage();
}