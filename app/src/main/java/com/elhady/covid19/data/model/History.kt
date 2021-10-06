package com.elhady.covid19.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class History(
    @Json(name = "country")
    var country: String? = null,
    @Json(name = "province")
    var province: List<String>? = null,
    @Json(name = "timeline")
    var timeline: Timeline? = null

)