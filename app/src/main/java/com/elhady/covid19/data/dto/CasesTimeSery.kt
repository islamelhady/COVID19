package com.elhady.covid19.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by islam elhady on 26-May-21.
 */
@Parcelize
data class CasesTimeSery(
    val dailyconfirmed: String?,
    val dailydeceased: String?,
    val dailyrecovered: String?,
    val date: String?,
    val dateymd: String?,
    val totalconfirmed: String?,
    val totaldeceased: String?,
    val totalrecovered: String?
): Parcelable