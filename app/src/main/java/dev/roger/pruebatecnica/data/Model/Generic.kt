package dev.roger.pruebatecnica.data.Model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "generic")
data class Generic constructor(
    /*@PrimaryKey(autoGenerate = true)
    var id: Int = 0,*/
    @PrimaryKey
    @SerializedName("page")
    var pageNumber: Int = 0,
    @Ignore
    @SerializedName("results")
    var series: List<Serie> = emptyList(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0,
) {
    constructor(pageNumber: Int, totalPages: Int, totalResults: Int) : this() {
    }
}