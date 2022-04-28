package dev.roger.pruebatecnica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    val api_key = "c6aeee577586ba38e487b74dfede5deb"
    val BASE_URL = "https://api.themoviedb.org/3/tv/popular?api_key="+ api_key +"&language=en-US&page="

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}