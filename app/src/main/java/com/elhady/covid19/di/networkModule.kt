package com.elhady.covid19.di

import com.elhady.covid19.data.remote.repository.CovidRepository
import com.elhady.covid19.data.remote.service.CovidService
import com.elhady.covid19.data.remote.service.CovidService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by islam elhady on 26-May-21.
 */
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            .build()
            .create(CovidService::class.java)
    }

    single {
        CovidRepository(get())
    }

}