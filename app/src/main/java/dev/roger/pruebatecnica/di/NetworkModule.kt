package dev.roger.pruebatecnica.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.roger.pruebatecnica.data.network.SeriesApiClient
import dev.roger.pruebatecnica.data.room.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val api_key = "c6aeee577586ba38e487b74dfede5deb"
    val BASE_URL2 = "https://api.themoviedb.org/3/tv/popular?api_key=c6aeee577586ba38e487b74dfede5deb&language=en-US&page=1"
    val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun provideSalesApiClient(retrofit: Retrofit): SeriesApiClient {
        return  retrofit.create(SeriesApiClient::class.java)
    }
}