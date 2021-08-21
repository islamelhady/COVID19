package com.elhady.covid19.data.repository

import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.data.model.Global
import com.elhady.covid19.data.remote.ApiService
import com.elhady.covid19.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class CovidRepository(private val apiService: ApiService) {

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
}