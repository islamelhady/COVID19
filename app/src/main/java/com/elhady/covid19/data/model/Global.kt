package com.elhady.covid19.data.model

data class Global(
    val active: Int,
    val activePerOneMillion: Double?,
    val affectedCountries: Int?,
    val cases: Int = 0,
    val casesPerOneMillion: Int?,
    val critical: Int?,
    val criticalPerOneMillion: Double?,
    val deaths: Int,
    val deathsPerOneMillion: Double?,
    val oneCasePerPeople: Int?,
    val oneDeathPerPeople: Int?,
    val oneTestPerPeople: Int?,
    val population: Long?,
    val recovered: Int,
    val recoveredPerOneMillion: Double?,
    val tests: Long?,
    val testsPerOneMillion: Double?,
    val todayCases: Int,
    val todayDeaths: Int,
    val todayRecovered: Int?,
    val updated: Long = 0L
)