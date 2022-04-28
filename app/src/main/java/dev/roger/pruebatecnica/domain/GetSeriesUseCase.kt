package dev.roger.pruebatecnica.domain

import dev.roger.pruebatecnica.Implementations.SeriesRepositoryImplements
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val seriesRepository : SeriesRepositoryImplements) {


    suspend operator fun invoke() = seriesRepository.getSeriesPackage(1);
}