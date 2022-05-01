package dev.roger.pruebatecnica.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.data.daos.GenericDao
import dev.roger.pruebatecnica.data.daos.SerieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SeriesService @Inject constructor(
    private val api: SeriesApiClient,
    private val genericDao: GenericDao,
    private val serieDao: SerieDao,
) {
    val api_key = "c6aeee577586ba38e487b74dfede5deb"

    var generic: List<Generic> = emptyList<Generic>()
    var series: List<Serie> = emptyList<Serie>()

    suspend fun getSeries(language: String, i: Int): List<Serie> {
        return withContext(Dispatchers.IO) {
            //val response = api.getAllSeries(api_key,"en-US","1")
            Log.d("ROGER","NO ES NULL, antes api")
            val response = api.getAllSeries(language,i)
            Log.d("ROGER","NO ES NULL, despues api")
            //Insert generic object on room

                Log.d("ROGER","NO ES NULL")
                genericDao.insert(response.body()!!)

                //response.body()!!.series.forEach {                                                                                                                                                                                                                                       serie -> serieDao.insert(serie) }
                series = response.body()!!.series

                for (series in series) {
                    var count = serieDao.getCountSeries()
                    if (series.genre_ids.isEmpty()) {
                        series.genre_ids = intArrayOf(0, 0)
                    }
                    if (series.poster_path.isNullOrEmpty()) {
                        series.poster_path = ""
                    }
                    if (series.backdrop_path.isNullOrEmpty()) {
                        series.backdrop_path = ""
                    }
                    series.identifier = count+1
                    //Log.d("ROGER","SERIE INSERTADA" + series.toString())
                    serieDao.insert(series)
                }

                //serieDao.insertAll(response.body()!!.series)

                generic = genericDao.getGeneric()
                //series = serieDao.getConcretSeries(i*20,(i*20)-20)

                if (!generic.isNullOrEmpty()) {
                    Log.d("ROGER BBDD", generic.size.toString())
                }

                if (!series.isNullOrEmpty()) {
                    Log.d("ROGER BBDD SERIES", series.size.toString())
                }
                response.body()!!.series ?: emptyList()
        }
    }

    suspend fun getTotalPages(): Int {
        return withContext(Dispatchers.IO) {
            val response = api.getTotalPages()
            response.body()!!.totalPages
        }
    }

    suspend fun getSeriesNoWifi(language: String, i: Int): List<Serie> {
        return withContext(Dispatchers.IO) {
            generic = genericDao.getGeneric()
            series = serieDao.getConcretSeries((i*20)-1,(i*20)-20)

            if (!generic.isNullOrEmpty()) {
                Log.d("ROGER BBDD", generic.size.toString())
            }

            if (!series.isNullOrEmpty()) {
                Log.d("ROGER BBDD SERIES", series.size.toString())
            }

            series
        }
    }
}