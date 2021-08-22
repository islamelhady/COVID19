package com.elhady.covid19.di

import androidx.work.WorkManager
import com.elhady.covid19.ui.worker.NotificationWorkerManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val syncModule = module {
    single { WorkManager.getInstance(androidContext()) }
    single { NotificationWorkerManager(get()) }
}
