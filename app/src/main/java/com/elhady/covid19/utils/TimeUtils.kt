package com.elhady.covid19.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
fun getPeriod(past: Date): String {
    val now = Date()
    val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
    val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)
    val days = TimeUnit.MILLISECONDS.toDays(now.time - past.time)
    val months = TimeUnit.MILLISECONDS.toDays(now.time - past.time)

    return when {
        seconds < 60 -> {
            "Few seconds ago"
        }
        minutes < 60 -> {
            "$minutes minutes ago"
        }
        hours <= 1 -> {
            "$hours hour ${minutes % 60} min ago"
        }
        hours < 24 -> {
            "$hours hours ago"
        }
        days <= 1 -> {
            "$days day ago"
        }
        days < 10 -> {
            "$days days ago"
        }
        months <= 1 -> {
            "$months month ago"
        }
        months < 10 -> {
            "$months months ago"
        }
        else -> {
            SimpleDateFormat("dd/MM/yy").format(past).toString()
        }
    }
}

fun getPeriodWorldNews(past: String): String {
    val pastDate = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z").parse(past)
    return getPeriod(pastDate)
}