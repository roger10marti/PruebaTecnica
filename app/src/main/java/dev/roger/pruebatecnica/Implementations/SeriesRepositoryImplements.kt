package dev.roger.pruebatecnica.Implementations

import dev.roger.pruebatecnica.Interfaces.SeriesRepository
import dev.roger.pruebatecnica.data.Model.Generic
import javax.inject.Inject

class SeriesRepositoryImplements @Inject constructor() : SeriesRepository {
    override fun getSeriesPackage(pageNumber: Int): List<Generic> {
        TODO("Not yet implemented")
    }
}