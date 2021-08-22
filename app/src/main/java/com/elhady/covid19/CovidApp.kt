package com.elhady.covid19

import android.app.Application
import com.elhady.covid19.di.networkModule
import com.elhady.covid19.di.preferenceModule
import com.elhady.covid19.di.syncModule
import com.elhady.covid19.di.viewModelModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@InternalCoroutinesApi
class CovidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(networkModule)
            modules(viewModelModule)
            modules(preferenceModule)
            modules(syncModule)
        }
    }
}