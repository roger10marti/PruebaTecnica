package dev.roger.pruebatecnica.data.Model

import java.util.*

class Serie(
    val backdrop_path: String,
    val first_air_date: Date,
    val genre_ids: Array<Int>,
    val id: Int,
    val name: String,
    val origin_country: Array<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
) {
}