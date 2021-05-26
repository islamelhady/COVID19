package com.elhady.covid19.di

import com.elhady.covid19.ui.view.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by islam elhady on 26-May-21.
 */
val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}