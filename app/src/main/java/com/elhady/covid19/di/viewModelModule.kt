package com.elhady.covid19.di

import com.elhady.covid19.ui.countries.CountriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CountriesViewModel(get())
    }
}