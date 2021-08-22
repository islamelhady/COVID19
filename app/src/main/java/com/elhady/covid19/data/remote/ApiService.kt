package com.elhady.covid19.data.remote

import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.data.model.FaqResponse
import com.elhady.covid19.data.model.Global
import com.elhady.covid19.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("v2/countries")
    suspend fun getAllCountriesData(): Response<List<CountriesItem>>

    @GET("/v2/all")
    suspend fun getGlobalData(): Response<Global>

    @GET
    suspend fun getFaqs(@Url url: String): Response<FaqResponse>

    @GET
    suspend fun getNews(@Url url: String): Response<NewsResponse>

    companion object {
        const val BASE_URL = "https://corona.lmao.ninja/"
        const val FAQS_URL = "https://covid19-news.herokuapp.com/api/covid19/faqs"
        const val NEWS_URL = "https://covid19-news.herokuapp.com/api/covid19/news"

    }


}