package com.elhady.covid19.data.remote

import com.elhady.covid19.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("/v2/countries")
    suspend fun getAllCountriesData(@Query("sort") sort: String): Response<List<Country>>

    @GET("/v2/countries/{country}")
    suspend fun getAllCountriesData(@Path("country") country: String, @Query("strict") strict: Boolean): Response<Country>

    @GET("/v2/historical/{country}")
    suspend fun getCountryHistoricalData(@Path("country") country: String): Response<History>

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
        const val WORLD_NEWS_URL =
            "https://news.google.com/news?q=covid-19&hl=en-US&sort=date&gl=US&num=100&output=rss"


    }


}