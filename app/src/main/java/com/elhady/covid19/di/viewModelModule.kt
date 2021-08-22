package com.elhady.covid19.di

import com.elhady.covid19.ui.countries.CountriesViewModel
import com.elhady.covid19.ui.faqs.FaqsViewModel
import com.elhady.covid19.ui.news.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CountriesViewModel(get())
    }

    viewModel {
        FaqsViewModel(get())
    }

    viewModel {
        NewsViewModel(get())
    }
}