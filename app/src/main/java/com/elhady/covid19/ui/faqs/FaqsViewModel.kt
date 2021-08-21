package com.elhady.covid19.ui.faqs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.covid19.data.model.FaqResponse
import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.data.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FaqsViewModel(private val repository: CovidRepository) : ViewModel() {

    private val _faqsData = MutableLiveData<State<FaqResponse>>()
    val faqsData: LiveData<State<FaqResponse>>
        get() = _faqsData

    fun getFaqsData() {
        viewModelScope.launch {
            repository.getFaqs(ApiService.FAQS_URL).collect {
                _faqsData.value = it
            }
        }
    }
}