package dev.roger.pruebatecnica.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.roger.pruebatecnica.data.Model.Generic
import dev.roger.pruebatecnica.domain.GetSeriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase
): ViewModel(){

    val seriesModel = MutableLiveData<Generic>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getSeriesUseCase()

            if (!result.isNullOrEmpty()) {
                seriesModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}