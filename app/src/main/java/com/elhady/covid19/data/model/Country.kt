package com.elhady.covid19.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "updated")
    var updated: Long = 0L,
    @Json(name = "country")
    var country: String? = null,
    @Json(name = "countryInfo")
    var countryInfo: CountryInfo? = null,
    @Json(name = "cases")
    var cases: Int = 0,
    @Json(name = "todayCases")
    var todayCases: Int = 0,
    @Json(name = "deaths")
    var deaths: Int = 0,
    @Json(name = "todayDeaths")
    var todayDeaths: Int = 0,
    @Json(name = "recovered")
    var recovered: Int = 0,
    @Json(name = "active")
    var active: Int = 0,
    @Json(name = "critical")
    var critical: Int = 0,
    @Json(name = "casesPerOneMillion")
    var casesPerOneMillion: Double? = 0.toDouble(),
    @Json(name = "deathsPerOneMillion")
    var deathsPerOneMillion: Double? = 0.toDouble(),
    @Json(name = "tests")
    var tests: Int = 0,
    @Json(name = "testsPerOneMillion")
    var testsPerOneMillion: Double? = 0.toDouble()
) : Parcelable