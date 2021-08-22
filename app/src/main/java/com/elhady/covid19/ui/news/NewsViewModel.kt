package com.elhady.covid19.ui.news

import androidx.lifecycle.*
import com.elhady.covid19.data.local.DataStoreManager
import com.elhady.covid19.data.model.NewsResponse
import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.data.repository.CovidRepository
import com.elhady.covid19.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: CovidRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

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

    val toggleButton = dataStoreManager.readToggleButton.asLiveData()

    fun getToggleButton(newsType: Boolean) {
        viewModelScope.launch {
            dataStoreManager.toggleButton(isChecked = newsType)
        }
    }

//    private val _toggleButton = MutableLiveData<Boolean>()
//
//    // For public variables, prefer use LiveData just to read values.
//    val toggleButton: LiveData<Boolean>
//        get() = _toggleButton
//
//    fun toggleButton(newsType: Boolean) {
//        viewModelScope.launch {
//            dataStoreManager.toggleButton(isChecked = newsType).collect {
//                _toggleButton.value
//            }
//        }
//    }
}

//    fun getWorldNewsData() {
//        viewModelScope.launch {
//            repository.getWorldNews(ApiService.WORLD_NEWS_URL).collect {
//                _newsData.value = it
//            }
//        }
//    }
//}