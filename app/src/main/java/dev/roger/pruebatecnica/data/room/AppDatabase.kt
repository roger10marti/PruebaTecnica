package dev.roger.pruebatecnica.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.data.daos.GenericDao
import dev.roger.pruebatecnica.data.daos.SerieDao

@TypeConverters(DataConverters::class)
@Database(entities = [Serie::class,Generic::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genericDao(): GenericDao

    abstract fun serieDao(): SerieDao

    companion object {
        @Volatile private var  instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it}
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "series")
                .fallbackToDestructiveMigration()
                .build()
    }
}