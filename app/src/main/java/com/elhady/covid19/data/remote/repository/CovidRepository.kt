package com.elhady.covid19.data.remote.repository

import com.elhady.covid19.data.dto.CovidResponse
import com.elhady.covid19.data.remote.service.CovidService
import com.elhady.covid19.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Created by islam elhady on 26-May-21.
 */
class CovidRepository(val apiService: CovidService) {

    fun getData(): Flow<State<CovidResponse>> {
        return object : NetworkBoundRepository<CovidResponse>() {
            override suspend fun fetchFromRemote(): Response<CovidResponse> = apiService.getData()
        }.asFlow().flowOn(Dispatchers.IO)
    }
}