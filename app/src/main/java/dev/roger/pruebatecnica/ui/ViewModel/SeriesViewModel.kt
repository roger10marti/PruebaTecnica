package dev.roger.pruebatecnica.ui.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.data.Model.SerieProvider
import dev.roger.pruebatecnica.data.SerieRepository
import dev.roger.pruebatecnica.domain.GetAllSeriesUseCase
import dev.roger.pruebatecnica.domain.GetSeriesUseCase
import dev.roger.pruebatecnica.ui.view.MainActivity
import dev.roger.pruebatecnica.ui.view.SeriesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase,
    private val getAllSeriesUseCase: GetAllSeriesUseCase
): ViewModel(){

    val seriesModel = MutableLiveData<Serie>()
    val isLoading = MutableLiveData<Boolean>()

    var seriesList: List<Serie> = emptyList()
    lateinit var recyclerViewAdapter: SeriesAdapter

    //var listSeries: List<Serie> = emptyList()
    fun getLiveDataObserver(): List<Serie> {
        seriesList()
        return seriesList
    }

    fun onCreate() {

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getSeriesUseCase()

            if (!result.isNullOrEmpty()) {
                //listSeries = getSeriesRepo.getSeriesPackage();
                //listSeries = seriesProvider.series
                seriesModel.postValue(result[0])
                isLoading.postValue(false)
                seriesList = result

                recyclerViewAdapter.setListData(getLiveDataObserver())
                Log.d("ROGER", getLiveDataObserver().size.toString())
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

}