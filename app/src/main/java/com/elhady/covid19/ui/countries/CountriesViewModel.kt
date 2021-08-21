package com.elhady.covid19.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.data.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CovidRepository) : ViewModel() {

    private val _covidAllCountriesData = MutableLiveData<State<List<CountriesItem>>>()
    val covidAllCountriesData: LiveData<State<List<CountriesItem>>>
        get() = _covidAllCountriesData


    fun getAllCountriesData() {
        viewModelScope.launch {
            repository.getAllCountriesData().collect {
                _covidAllCountriesData.value = it
            }
        }
    }
}