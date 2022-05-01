package dev.roger.pruebatecnica.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import dev.roger.pruebatecnica.data.daos.GenericDao
import dev.roger.pruebatecnica.data.daos.SerieDao
import dev.roger.pruebatecnica.data.room.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    public fun provideDataBase(application : Application, roomCallback : RoomDatabase.Callback) : AppDatabase {
        return Room.databaseBuilder(
            application.getApplicationContext(),
            AppDatabase::class.java,
            "custom_database"
        )
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build()
    }

    @Provides
    public fun provideRoomDatabaseCallback() : RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db : SupportSQLiteDatabase) {
                //Initialize Database if no database attached to the App
                super.onCreate(db)
            }

            override fun onOpen(db : SupportSQLiteDatabase) {
                //Re-open Database if it has database attached to the App
                super.onOpen(db)
            }
        }
    }

    @Provides
    public fun provideSerieDAO(customDatabase : AppDatabase) : SerieDao {
        return customDatabase.serieDao()
    }

    @Provides
    public fun provideGenericDAO(customDatabase : AppDatabase) : GenericDao {
        return customDatabase.genericDao()
    }
}