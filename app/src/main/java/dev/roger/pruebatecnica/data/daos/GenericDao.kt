package dev.roger.pruebatecnica.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.Provides
import dev.roger.pruebatecnica.data.Model.Generic

@Dao
interface GenericDao {
    @Query("SELECT * FROM generic")
    fun getGeneric() : List<Generic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(generic: List<Generic>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(generic: Generic)
}