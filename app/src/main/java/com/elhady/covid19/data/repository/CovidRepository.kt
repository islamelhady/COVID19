package com.elhady.covid19.data.repository

import com.elhady.covid19.data.model.*
import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class CovidRepository(private val apiService: ApiService
//, private val parser: Parser
) {

    fun getAllCountriesData(sort: String): Flow<State<List<Country>>> {
        return object : NetworkBoundRepository<List<Country>>() {
            override suspend fun fetchFromRemote(): Response<List<Country>> =
                apiService.getAllCountriesData(sort)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getAllCountriesData(country: String, strict: Boolean): Flow<State<Country>> {
        return object : NetworkBoundRepository<Country>() {
            override suspend fun fetchFromRemote(): Response<Country> =
                apiService.getAllCountriesData(country, strict)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getCountryHistoricalData(country: String): Flow<State<History>> {
        return object : NetworkBoundRepository<History>() {
            override suspend fun fetchFromRemote(): Response<History> =
                apiService.getCountryHistoricalData(country)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getGlobalData(): Flow<State<Global>> {
        return object : NetworkBoundRepository<Global>() {
            override suspend fun fetchFromRemote(): Response<Global> = apiService.getGlobalData()
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getFaqs(url: String): Flow<State<FaqResponse>> {
        return object : NetworkBoundRepository<FaqResponse>() {
            override suspend fun fetchFromRemote(): Response<FaqResponse> =
                apiService.getFaqs(url)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getNews(url: String): Flow<State<NewsResponse>> {
        return object : NetworkBoundRepository<NewsResponse>() {
            override suspend fun fetchFromRemote(): Response<NewsResponse> =
                apiService.getNews(url)
        }.asFlow().flowOn(Dispatchers.IO)
    }

//    fun getWorldNews(url: String): Flow<State<NewsResponse>> {
//        return object : WorldNewsBoundRepository() {
//            override suspend fun fetchFromRemote(): MutableList<Article> =
//                parser.getChannel(url).articles
//        }.asFlow().flowOn(Dispatchers.IO)
//    }
}