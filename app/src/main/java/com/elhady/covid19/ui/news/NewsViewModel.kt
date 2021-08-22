package com.elhady.covid19.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.covid19.data.model.NewsResponse
import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.data.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: CovidRepository) : ViewModel() {

    private val _newsData = MutableLiveData<State<NewsResponse>>()
    val newsData: LiveData<State<NewsResponse>>
        get() = _newsData

    fun getNewsData() {
        viewModelScope.launch {
            repository.getNews(ApiService.NEWS_URL).collect {
                _newsData.value = it
            }
        }
    }
}