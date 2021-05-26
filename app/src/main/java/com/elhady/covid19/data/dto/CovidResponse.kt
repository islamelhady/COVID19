package com.elhady.covid19.data.dto

/**
 * Created by islam elhady on 26-May-21.
 */
data class CovidResponse(
    val cases_time_series: List<CasesTimeSery>?,
    val statewise: List<Statewise>?
)