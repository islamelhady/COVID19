package com.elhady.covid19.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Timeline(
    @Json(name = "cases")
    var cases: Map<String, Int>? = null,
    @Json(name = "deaths")
    var deaths: Map<String, Int>? = null,
    @Json(name = "recovered")
    var recovered: Map<String, Int>? = null

)