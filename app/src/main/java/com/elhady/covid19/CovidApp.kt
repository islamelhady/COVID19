package com.elhady.covid19

import android.app.Application
import com.elhady.covid19.di.networkModule
import com.elhady.covid19.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(networkModule)
            modules(viewModelModule)
        }
    }
}