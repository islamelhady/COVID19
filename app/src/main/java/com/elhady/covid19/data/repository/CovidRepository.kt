package com.elhady.covid19.data.repository

import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.data.model.FaqResponse
import com.elhady.covid19.data.model.Global
import com.elhady.covid19.data.model.NewsResponse
import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.utils.State
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class CovidRepository(private val apiService: ApiService
//, private val parser: Parser
) {

    fun getAllCountriesData(): Flow<State<List<CountriesItem>>> {
        return object : NetworkBoundRepository<List<CountriesItem>>() {
            override suspend fun fetchFromRemote(): Response<List<CountriesItem>> =
                apiService.getAllCountriesData()
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