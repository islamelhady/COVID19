package com.elhady.covid19.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.covid19.data.model.Global
import com.elhady.covid19.data.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GlobalViewModel(private val repository: CovidRepository) : ViewModel() {

    private val _covidGlobalData = MutableLiveData<State<Global>>()
    val covidGlobalData: LiveData<State<Global>>
        get() = _covidGlobalData

    fun getGlobalData() {
        viewModelScope.launch {
            repository.getGlobalData().collect {
                _covidGlobalData.value = it
            }
        }
    }
}