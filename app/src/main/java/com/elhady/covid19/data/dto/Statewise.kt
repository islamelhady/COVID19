package com.elhady.covid19.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by islam elhady on 26-May-21.
 */
@Parcelize
data class Statewise(
    val active: String?,
    val confirmed: String?,
    val deaths: String?,
    val deltaconfirmed: String?,
    val deltadeaths: String?,
    val deltarecovered: String?,
    val lastupdatedtime: String?,
    val migratedother: String?,
    val recovered: String?,
    val state: String?,
    val statecode: String?,
    val statenotes: String?
) : Parcelable