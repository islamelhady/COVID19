package com.elhady.covid19.data.remote

import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.data.model.FaqResponse
import com.elhady.covid19.data.model.Global
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {


//

//    @GET("v3/covid-19/countries")

    @GET("v2/countries")
    suspend fun getAllCountriesData(): Response<List<CountriesItem>>

    @GET("/v2/all")
    suspend fun getGlobalData(): Response<Global>

    @GET
    suspend fun getFaqs(@Url url: String): Response<FaqResponse>


    companion object {
        const val BASE_URL = "https://corona.lmao.ninja/"
//        const val BASE_URL = "https://disease.sh/"
        const val FAQS_URL = "https://covid19-news.herokuapp.com/api/covid19/faqs"
    }


}