package com.elhady.covid19.ui.global

import androidx.lifecycle.ViewModel
import com.elhady.covid19.data.repository.CovidRepository

class GlobalViewModel(private val repository: CovidRepository) : ViewModel() {

//    private val _covidGlobalData = MutableLiveData<State<Global>>()
//    val covidGlobalData: LiveData<State<Global>>
//        get() = _covidGlobalData
//
//    fun getGlobalData() {
//        viewModelScope.launch {
//            repository.getGlobalData().collect {
//                _covidGlobalData.value = it
//            }
//        }
//    }
}