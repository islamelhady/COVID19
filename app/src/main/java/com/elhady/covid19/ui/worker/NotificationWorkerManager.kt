package com.elhady.covid19.ui.worker

import androidx.work.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class NotificationWorkerManager(private val workManager: WorkManager) {
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private lateinit var notificationWorkRequest: PeriodicWorkRequest

    fun initialize(interval: Long) {
        notificationWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(interval, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        start()
    }

    fun start() {
        workManager.enqueueUniquePeriodicWork(
            JOB_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            notificationWorkRequest
        )
    }

    companion object {
        const val JOB_TAG = "notificationWorkTag"
    }
}