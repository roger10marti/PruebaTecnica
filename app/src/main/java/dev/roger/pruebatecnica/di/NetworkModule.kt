package dev.roger.pruebatecnica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.roger.pruebatecnica.data.network.SeriesApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val api_key = "c6aeee577586ba38e487b74dfede5deb"
    val BASE_URL = "https://api.themoviedb.org/3/tv/popular?api_key="+ api_key +"&language=en-US&page="

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