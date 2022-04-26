package dev.roger.pruebatecnica.Interfaces

import dev.roger.pruebatecnica.Model.Generic

interface SeriesRepository {
    fun getSeriesPackage(pageNumber: Int): List<Generic>
}