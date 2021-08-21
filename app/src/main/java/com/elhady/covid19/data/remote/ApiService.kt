package com.elhady.covid19.data.remote

import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.data.model.Global
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


//    @GET("v2/countries")

    @GET("v3/covid-19/countries")
    suspend fun getAllCountriesData(): Response<List<CountriesItem>>

    @GET("/v2/all")
    suspend fun getGlobalData(): Response<Global>


    companion object {
//        const val BASE_URL = "https://corona.lmao.ninja/"
        const val BASE_URL = "https://disease.sh/"
    }

//    @GET("remote-jobs")
//    suspend fun getAllJobs(@Query("limit") limit: String): Response<RemoteJob>
//
//
//    companion object{
//        const val BASE_URL = "https://remotive.io/api/"
//    }

}