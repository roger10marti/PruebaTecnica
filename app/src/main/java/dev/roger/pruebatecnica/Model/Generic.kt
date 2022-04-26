package dev.roger.pruebatecnica.Model

data class Generic(
    val pageNumber: Int,
    val series: ArrayList<Serie>,
    val totalPages: Int,
    val totalResults: Int,
) {
}