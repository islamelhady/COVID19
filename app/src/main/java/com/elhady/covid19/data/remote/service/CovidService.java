package com.elhady.covid19.data.remote.service;

import com.elhady.covid19.data.dto.CovidResponse;

/**
 * Created by islam elhady on 26-May-21.
 */
public interface CovidService {

    @GET("data.json")
    suspend fun getData(): Response<CovidResponse>


    companion object{
        const val BASE_URL = "https://api.covid19india.org/"
    }
}
