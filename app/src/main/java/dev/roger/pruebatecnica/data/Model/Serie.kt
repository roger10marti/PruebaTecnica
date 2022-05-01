package dev.roger.pruebatecnica.data.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "serie")
data class Serie constructor(
    //@PrimaryKey(autoGenerate = true)
    var identifier: Int = 0,
    @SerializedName("backdrop_path")
    var backdrop_path: String= "",
    @SerializedName("first_air_date")
    var first_air_date: String = "",
    @Ignore
    @SerializedName("genre_ids")
    var genre_ids: IntArray = intArrayOf(0,0),
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @Ignore
    @SerializedName("origin_country")
    var origin_country: Array<String> = emptyArray<String>(),
    @SerializedName("original_language")
    var original_language: String = "",
    @SerializedName("original_name")
    var original_name: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    var poster_path: String = "",
    @SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @SerializedName("vote_count")
    var vote_count: Int = 0,
) {

    constructor(identifier: Int,backdrop_path: String,first_air_date: String,id: Int,name: String,original_language: String,
    original_name: String,overview: String,popularity: Double,poster_path: String,vote_average: Double,vote_count: Int):this(){


    }

    override fun toString(): String {
        return "Serie(identifier=$identifier, backdrop_path='$backdrop_path', first_air_date='$first_air_date', genre_ids=${genre_ids.contentToString()}, id=$id, name='$name', origin_country=${origin_country.contentToString()}, original_language='$original_language', original_name='$original_name', overview='$overview', popularity=$popularity, poster_path='$poster_path', vote_average=$vote_average, vote_count=$vote_count)"
    }
    /*constructor(identifier: Int, backdrop_path: String, first_air_date: String, genre_ids: Array<Int>, id: Int, name: String, origin_country: Array<String>, original_language: String, original_name:
    String, overview: String, popularity: Double, poster_path: String, vote_average: Double, vote_count: Int) :
    this(identifier, backdrop_path, first_air_date, genre_ids, id, name, origin_country, original_language, original_name, overview, popularity, poster_path, vote_average, vote_count)
    {
    }*/
}