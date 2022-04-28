package dev.roger.pruebatecnica.data.Model

data class Generic(
    val pageNumber: Int,
    val series: ArrayList<Serie>,
    val totalPages: Int,
    val totalResults: Int,
) {
}