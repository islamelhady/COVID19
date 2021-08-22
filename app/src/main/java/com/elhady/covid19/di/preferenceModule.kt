package com.elhady.covid19.di

import com.elhady.covid19.data.local.DataStoreManager
import org.koin.dsl.module

val preferenceModule = module{
    single { DataStoreManager(get()) }
}