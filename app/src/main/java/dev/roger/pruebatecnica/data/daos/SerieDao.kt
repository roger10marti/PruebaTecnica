package dev.roger.pruebatecnica.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.roger.pruebatecnica.data.Model.Serie

@Dao
interface SerieDao {
    @Query("SELECT * FROM serie where ((identifier <= :page) and (identifier >= :max))")
    fun getConcretSeries(page: Int, max: Int) : List<Serie>

    @Query("SELECT Count(*) FROM serie")
    fun getCountSeries() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(serie: List<Serie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serie: Serie)
}