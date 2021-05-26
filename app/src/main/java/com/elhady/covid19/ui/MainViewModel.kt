package com.elhady.covid19.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.covid19.data.dto.CovidResponse
import com.elhady.covid19.data.remote.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by islam elhady on 26-May-21.
 */
class MainViewModel(val repository: CovidRepository) : ViewModel() {

    private val _covidLiveData = MutableLiveData<State<CovidResponse>>()
    val covidLiveData: LiveData<State<CovidResponse>>
        get() = _covidLiveData

    fun getData() {
        viewModelScope.launch {
            repository.getData().collect {
                _covidLiveData.value = it
            }
        }
    }
}