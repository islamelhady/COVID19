package com.elhady.covid19.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GlabalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is global Fragment"
    }
    val text: LiveData<String> = _text
}