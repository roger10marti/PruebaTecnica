package dev.roger.pruebatecnica.domain

import dev.roger.pruebatecnica.data.SerieRepository
import javax.inject.Inject

class GetTotalPagesUseCase @Inject constructor(private val seriesRepository : SerieRepository){
    suspend operator fun invoke() = seriesRepository.getTotalPages();
}