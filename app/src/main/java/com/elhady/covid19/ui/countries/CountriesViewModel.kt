package com.elhady.covid19.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.covid19.data.model.Country
import com.elhady.covid19.data.model.History
import com.elhady.covid19.data.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CovidRepository) : ViewModel() {

    private val _covidAllCountriesData = MutableLiveData<State<List<Country>>>()
    val covidAllCountriesData: LiveData<State<List<Country>>>
        get() = _covidAllCountriesData

    fun getAllCountriesData(sort: String) {
        viewModelScope.launch {
            repository.getAllCountriesData(sort).collect {
                _covidAllCountriesData.value = it
            }
        }
    }

    private val _covidAllCountriesDataSearch = MutableLiveData<State<Country>>()
    val covidAllCountriesDataSearch: LiveData<State<Country>>
        get() = _covidAllCountriesDataSearch

    fun getAllCountriesData(country: String, strict: Boolean) {
        viewModelScope.launch {
            repository.getAllCountriesData(country, strict).collect {
                _covidAllCountriesDataSearch.value = it
            }
        }
    }

    private val _covidCountryHistoricalData = MutableLiveData<State<History>>()
    val covidCountryHistoricalData: LiveData<State<History>>
        get() = _covidCountryHistoricalData

    fun getCountryHistoricalData(country: String) {
        viewModelScope.launch {
            repository.getCountryHistoricalData(country).collect {
                _covidCountryHistoricalData.value = it
            }
        }
    }
}