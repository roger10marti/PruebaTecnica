package dev.roger.pruebatecnica.data.Model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SerieProvider @Inject constructor() {
    var series: List<Generic> = emptyList();
}