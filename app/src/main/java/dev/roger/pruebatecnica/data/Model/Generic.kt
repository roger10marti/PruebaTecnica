package dev.roger.pruebatecnica.data.Model

import com.google.gson.annotations.SerializedName

data class Generic(
    @SerializedName("page")
    val pageNumber: Int,
    @SerializedName("results")
    val series: List<Serie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
) {
}