package com.elhady.covid19.di

import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.data.remote.ApiService.Companion.BASE_URL
import com.elhady.covid19.data.repository.CovidRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
//            .client(getOkHttpClient(androidContext()))
            .build()
            .create(ApiService::class.java)
    }
    single {
        CovidRepository(get())
    }
}