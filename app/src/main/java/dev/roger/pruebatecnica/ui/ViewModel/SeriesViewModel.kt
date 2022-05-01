package dev.roger.pruebatecnica.ui.ViewModel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.data.Model.SerieProvider
import dev.roger.pruebatecnica.data.SerieRepository
import dev.roger.pruebatecnica.data.daos.GenericDao
import dev.roger.pruebatecnica.domain.GetAllSeriesUseCase
import dev.roger.pruebatecnica.domain.GetSeriesUseCase
import dev.roger.pruebatecnica.domain.GetSeriesWithoutConnection
import dev.roger.pruebatecnica.domain.GetTotalPagesUseCase
import dev.roger.pruebatecnica.ui.view.MainActivity
import dev.roger.pruebatecnica.ui.view.SeriesAdapter
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase,
    private val getAllSeriesUseCase: GetAllSeriesUseCase,
    private val getTotalPagesUseCase: GetTotalPagesUseCase,
    private val getSeriesWithoutConnection: GetSeriesWithoutConnection
): ViewModel(){

    val seriesModel = MutableLiveData<Serie>()
    val isLoading = MutableLiveData<Boolean>()
    var totalPages: Int = 1
    var languageCode: String = "en-US"

    var seriesList: List<Serie> = emptyList()
    var actualPageValue: Int = 1
    lateinit var recyclerViewAdapter: SeriesAdapter

    fun getLiveDataObserver(): List<Serie> {
        seriesList()
        return seriesList
    }

    fun onCreate() {

        viewModelScope.launch {
            isLoading.postValue(true)

            Log.d("ROGER", Locale.getDefault().language)
            if (Locale.getDefault().language == "es") languageCode = "es-ES"
            else languageCode = "en-US"

            val result = getSeriesUseCase(languageCode,actualPageValue)
            totalPages = getTotalPagesUseCase()

            if (!result.isNullOrEmpty()) {
                seriesModel.postValue(result[0])
                isLoading.postValue(false)
                seriesList = result

                recyclerViewAdapter.setListData(getLiveDataObserver())
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    fun onCreateWithoutConnection() {

        viewModelScope.launch {
            isLoading.postValue(true)

            Log.d("ROGER", Locale.getDefault().language)
            if (Locale.getDefault().language == "es") languageCode = "es-ES"
            else languageCode = "en-US"

            val result = getSeriesWithoutConnection.invoke(languageCode,actualPageValue)
            totalPages = 5800

            if (!result.isNullOrEmpty()) {
                seriesModel.postValue(result[0])
                isLoading.postValue(false)
                seriesList = result

                recyclerViewAdapter.setListData(getLiveDataObserver())
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    fun seriesList() {
        isLoading.postValue(true)
        val serie = getAllSeriesUseCase()
        if(serie!=null){
            seriesModel.postValue(serie[4])
            seriesList = serie
        }
        isLoading.postValue(false)
    }

    fun buttonFunctionality(i:Int,connection: Boolean) {
        if (connection) {
            apiCall(i)
        } else{
            getDataWithoutConnection(i)
        }
    }

    fun getDataWithoutConnection(i: Int) {
        viewModelScope.launch {
            actualPageValue = actualPageValue + i
            val result = getSeriesWithoutConnection.invoke(languageCode,actualPageValue)
            totalPages = 5800

            if (!result.isNullOrEmpty()) {
                seriesModel.postValue(result[0])
                isLoading.postValue(false)
                seriesList = result

                recyclerViewAdapter.setListData(getLiveDataObserver())
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    fun apiCall(i: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            actualPageValue = actualPageValue + i
            val result = getSeriesUseCase(languageCode,actualPageValue)

            if (!result.isNullOrEmpty()) {
                seriesModel.postValue(result[0])
                isLoading.postValue(false)
                seriesList = result

                recyclerViewAdapter.setListData(getLiveDataObserver())
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    fun buttonControl(buttonNext: Button, buttonPrevious: Button) {
        if (actualPageValue == totalPages) {
            buttonNext.isEnabled = false
            buttonPrevious.isEnabled = true
        } else {
            buttonNext.isEnabled = true
        }
        if (actualPageValue == 1) {
            buttonPrevious.isEnabled = false
            buttonNext.isEnabled = true
        } else {
            buttonPrevious.isEnabled = true
        }
    }
}
